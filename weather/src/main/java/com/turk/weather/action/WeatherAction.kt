package com.turk.weather.action

import com.turk.common.error.ErrorEntity
import com.turk.common.mvi.ViewAction
import com.turk.dtos.city.City
import com.turk.dtos.weather.Weather
import com.turk.weather.state.WeatherState

sealed class WeatherAction:ViewAction{

     object DEFAULT : WeatherAction()
     object SHOW_LOADING : WeatherAction()

     object SwipeRefreshLoading  : WeatherAction()

     object ChangeTempratureToCelcius : WeatherAction()
     object ChangeTempratureToKelvin : WeatherAction()

     object RefreshWeatherReport : WeatherAction()
     object GetLocationAndRefresh : WeatherAction()
     data class FetchCurrentWeather(val lon:Double,val lat:Double) : WeatherAction()
     data class FetchWeatherForCity(val cityName:String) : WeatherAction()
     data class FetchWeatherForecast(val cityId:Int) : WeatherAction()

     data class DisplayWeatherData(val data:Weather) : WeatherAction()
     data class DisplayWeatherForecastData(val data:List<Weather>) : WeatherAction()
     data class Error(val error:ErrorEntity) : WeatherAction()


}
