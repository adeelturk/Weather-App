package com.turk.weatherapp.navigation

import com.turk.dtos.city.City

interface MainRoutHandler {

     fun showCities()
    fun showWeatherReport(city: City)
}