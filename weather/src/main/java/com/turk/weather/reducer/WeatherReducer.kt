package com.turk.weather.reducer

import com.turk.common.mvi.Reducer
import com.turk.weather.action.WeatherAction
import com.turk.weather.state.WeatherState

class WeatherReducer : Reducer<WeatherState, WeatherAction> {

    override fun reduce(currentState: WeatherState, action: WeatherAction): WeatherState {

        return when (action) {

            is WeatherAction.DEFAULT -> {

                WeatherState.DEFAULT
            }
            is WeatherAction.FetchCurrentWeather -> {
                stateToFetchCurrentWeatherData()
            }

            is WeatherAction.DisplayWeatherData -> {
                stateToDisplayWeatherData(action)
            }

            is WeatherAction.Error -> {

                stateOnError(action)
            }

            else -> {
                currentState
            }
        }
    }

    private fun stateToFetchCurrentWeatherData(): WeatherState {

        return WeatherState.Loading
    }

    private fun stateToDisplayWeatherData(action: WeatherAction.DisplayWeatherData): WeatherState {

        return WeatherState.WeatherData(action.data)
    }

    private fun stateOnError(action: WeatherAction.Error): WeatherState {
        return WeatherState.Error(action.error)
    }

}