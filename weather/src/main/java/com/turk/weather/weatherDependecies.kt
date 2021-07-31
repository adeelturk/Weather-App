package com.turk.weather

import com.turk.common.location.LocationTrack
import com.turk.weather.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weatherDependencies= module {

    viewModel { WeatherViewModel(get(),get(),get()) }
    factory{ LocationTrack(get()) }
}