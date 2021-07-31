package com.turk.weather.state

import com.turk.common.error.ErrorEntity
import com.turk.common.mvi.ViewState
import com.turk.dtos.city.City
import com.turk.dtos.weather.Weather

sealed class WeatherState:ViewState{

     object DEFAULT : WeatherState()
     object Loading : WeatherState()
     object SwipeRefreshLoading : WeatherState()
     object GetLocationAndRefresh : WeatherState()
     data class WeatherData(val data: Weather): WeatherState()
     data class WeatherForecastData(val data:List<Weather>): WeatherState()
     data class Error(val error: ErrorEntity) : WeatherState()
}
