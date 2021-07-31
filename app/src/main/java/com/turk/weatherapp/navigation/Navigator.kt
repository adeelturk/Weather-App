package com.turk.weatherapp.navigation

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.turk.cities.routing.CityNavigation
import com.turk.dtos.city.City
import com.turk.weather.rounting.WeatherNavigation
import com.turk.weatherapp.R

class Navigator(private val applicationContext: Context) :
    WeatherNavigation, CityNavigation {

    private var navController: MainRoutHandler?=null

    fun setNavigationController(navController: MainRoutHandler){
        this.navController=navController
    }
    override fun showCities() {

        navController?.showCities()
    }


    override fun showWeatherReport(city: City) {
        navController?.showWeatherReport(city)
    }
}

