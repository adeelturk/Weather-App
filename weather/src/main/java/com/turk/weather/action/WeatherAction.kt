package com.turk.weather.action

import com.turk.common.error.ErrorEntity
import com.turk.common.mvi.ViewAction
import com.turk.dtos.city.City
import com.turk.dtos.weather.Weather

sealed class WeatherAction:ViewAction{

     object DEFAULT : WeatherAction()
     data class FetchCurrentWeather(val lon:Double,val lat:Double) : WeatherAction()
     data class DisplayWeatherData(val data:Weather) : WeatherAction()
     data class Error(val error:ErrorEntity) : WeatherAction()


}
