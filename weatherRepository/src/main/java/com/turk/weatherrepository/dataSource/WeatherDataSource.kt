package com.turk.weatherrepository.dataSource


import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.serverObjects.WeatherEntity
import com.turk.dtos.weather.Weather
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface WeatherDataSource {


    fun getWeatherReport(lat:Double,lon:Double):Flow<Either<ErrorEntity,Weather>>

    fun getWeatherReport(cityName:String):Flow<Either<ErrorEntity,Weather>>

}