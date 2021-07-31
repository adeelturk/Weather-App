package com.turk.weather.viewmodel

import androidx.lifecycle.viewModelScope
import com.turk.business.usecase.weather.*
import com.turk.common.base.BaseViewModel
import com.turk.common.error.ErrorEntity
import com.turk.common.extension.empty
import com.turk.common.extension.zero
import com.turk.dtos.const.WeatherUnit
import com.turk.dtos.weather.Weather
import com.turk.weather.action.WeatherAction
import com.turk.weather.reducer.WeatherReducer
import com.turk.weather.state.WeatherState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WeatherViewModel(
    private val weatherCityUseCase: WeatherCityUseCase,
    private val weatherForecastsByCityIdUseCase: WeatherForecastsByCityIdUseCase,
    private val changeTemperatureUnitUseCase: ChangeTemperatureUnitUseCase
) : BaseViewModel<WeatherState, WeatherAction>
    (WeatherState.DEFAULT, WeatherReducer()) {


    override fun processAction(action: WeatherAction) {
        super.processAction(action)
        when (action) {

            is WeatherAction.FetchCurrentWeather -> {
                fetchWeatherReport(
                    WeatherReportParams(
                        String.empty(),
                        action.lat,
                        action.lon,
                        true
                    )
                )
            }
            is WeatherAction.FetchWeatherForCity -> {
                fetchWeatherReport(
                    WeatherReportParams(
                        action.cityName,
                        Double.zero(),
                        Double.zero(),
                        false
                    )
                )
            }
            is WeatherAction.FetchWeatherForecast -> {
                fetchWeatherForecasts(action.cityId)
            }

            is WeatherAction.RefreshWeatherReport -> {

                if (weatherData != null) {

                    fetchWeatherForecasts(weatherData!!.id)
                } else {

                    dispatch(WeatherAction.GetLocationAndRefresh)

                }
            }
            is WeatherAction.ChangeTempratureToCelcius->{
                changeTemperatureUnit(WeatherUnit.CELCIUS)
            }
            is WeatherAction.ChangeTempratureToKelvin->{
                changeTemperatureUnit(WeatherUnit.KELVIN)

            }
            else -> {

            }
        }
    }

     var weatherData: Weather? = null

    private fun fetchWeatherReport(params: WeatherReportParams) {

        weatherCityUseCase(viewModelScope = viewModelScope, params) {

            it.either(::handleFailure) {

                dispatch(WeatherAction.DisplayWeatherData(it))
                weatherData = it
                fetchWeatherForecasts(it.id)
            }
        }
    }

    private val _weather = MutableStateFlow(listOf<Weather>())
    val weatherForecastDat: StateFlow<List<Weather>> = _weather
    private fun fetchWeatherForecasts(cityId: Int) {
        dispatch(WeatherAction.SwipeRefreshLoading)
        weatherForecastsByCityIdUseCase(
            viewModelScope = viewModelScope,
            WeatherForecastParams(cityId)
        ) {
            it.either(::handleFailure) {

                dispatch(WeatherAction.DisplayWeatherForecastData(it))
                _weather.value = it
            }

        }
    }

    private fun changeTemperatureUnit(unit: WeatherUnit) {

            changeTemperatureUnitUseCase(
                viewModelScope,
                ChangeTemperatureUnitUseCaseParams(weatherData!!, weatherForecastDat.value, unit)
            ) {

                it.either(::handleFailure) {

                    dispatch(WeatherAction.DisplayWeatherData(it.currentCityWeather))
                    weatherData = it.currentCityWeather
                    dispatch(WeatherAction.DisplayWeatherForecastData(it.foreCast))
                    _weather.value = it.foreCast

                }
            }



    }

}