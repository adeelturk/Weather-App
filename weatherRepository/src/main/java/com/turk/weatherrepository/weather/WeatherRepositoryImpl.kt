package com.turk.weatherrepository.weather

import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.weather.Weather
import com.turk.dtos.weather.WeatherReport
import com.turk.weatherrepository.dataSource.WeatherDataSourceImpl
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl(private val weatherDataSourceImp:WeatherDataSourceImpl) :WeatherRepository{


    override fun getWeatherReport(lat: Double, lon: Double): Flow<Either<ErrorEntity, Weather>> =
        weatherDataSourceImp.getWeatherReport(lat,lon)

    override fun getWeatherReport(cityName: String): Flow<Either<ErrorEntity, Weather>> =
        weatherDataSourceImp.getWeatherReport(cityName)

    override fun getWeatherForecastReport(cityId: Int): Flow<Either<ErrorEntity, List<Weather>>> =
        weatherDataSourceImp.getWeatherForecastReport(cityId)

    override fun getWeatherDatInClecius(data: WeatherReport): Flow<Either<ErrorEntity, WeatherReport>> =
        weatherDataSourceImp.getWeatherDatInClecius(data)


    override fun getWeatherDatInKelivin(data: WeatherReport): Flow<Either<ErrorEntity, WeatherReport>> =
        weatherDataSourceImp.getWeatherDatInKelivin(data)
}