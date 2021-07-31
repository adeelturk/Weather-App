package com.turk.dtos.weather

data class WeatherReport(val currentCityWeather:Weather, val foreCast: List<Weather>)
