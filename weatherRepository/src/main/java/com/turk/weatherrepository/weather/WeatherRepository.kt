package com.turk.weatherrepository.weather

import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.weather.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {


    fun getWeatherReport(lat:Double,lon:Double): Flow<Either<ErrorEntity, Weather>>

    fun getWeatherReport(cityName:String): Flow<Either<ErrorEntity, Weather>>
}