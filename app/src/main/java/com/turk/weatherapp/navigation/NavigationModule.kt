package com.turk.weatherapp.navigation

import com.turk.cities.routing.CityNavigation
import com.turk.weather.rounting.WeatherNavigation
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.binds
import org.koin.dsl.module

val navigationModule = module {

    single { Navigator(androidApplication()) } binds  arrayOf( WeatherNavigation::class , CityNavigation::class )

}