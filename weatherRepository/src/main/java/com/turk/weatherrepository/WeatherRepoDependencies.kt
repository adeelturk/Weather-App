package com.turk.weatherrepository

import com.turk.dtos.mapper.WeatherDataMapper
import com.turk.weatherrepository.dataSource.WeatherDataSourceImpl
import com.turk.weatherrepository.weather.WeatherRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val weatherRepoDependencies= module{

    //retrofit
    single { get<Retrofit>().create(WeatherApiService::class.java) }

    // Dto Mapper
    single { WeatherDataMapper() }

    //dataSource
    single { WeatherDataSourceImpl(get(),get(),get())}



    //repositories
    single { WeatherRepositoryImpl(get()) }
}