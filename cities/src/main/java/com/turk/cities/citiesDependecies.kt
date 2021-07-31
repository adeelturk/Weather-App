package com.turk.cities

import com.turk.cities.viewmodel.CitiesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val citiesDependencies= module{

    viewModel { CitiesViewModel(BuildConfig.CITIES_LIST_URL,get(),get(),get()) }

}