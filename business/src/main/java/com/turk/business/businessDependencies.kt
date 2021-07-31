package com.turk.business

import com.turk.business.usecase.cities.CitiesListUseCase
import com.turk.business.usecase.cities.FavouriteCityUseCase
import com.turk.business.usecase.cities.SearchCityByName
import com.turk.business.usecase.weather.WeatherCityUseCase
import org.koin.dsl.module


val businessDependencies= module{

    single { CitiesListUseCase(get()) }
    single { SearchCityByName(get()) }
    single { FavouriteCityUseCase(get()) }
    single { WeatherCityUseCase(get()) }
}