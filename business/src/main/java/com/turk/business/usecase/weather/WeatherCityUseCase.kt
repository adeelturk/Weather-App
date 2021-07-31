package com.turk.business.usecase.weather

import com.turk.citiesrepository.city.CitiesRepository
import com.turk.citiesrepository.city.CitiesRepositoryImpl
import com.turk.common.base.BaseUseCase
import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.city.City
import com.turk.dtos.weather.Weather
import com.turk.weatherrepository.weather.WeatherRepositoryImpl
import kotlinx.coroutines.flow.Flow

class WeatherCityUseCase(private val repo: WeatherRepositoryImpl) :BaseUseCase<Weather,WeatherReportParams>() {


    override suspend fun run(param: WeatherReportParams): Flow<Either<ErrorEntity, Weather>> {

      return  if(param.isForLocation){

          repo.getWeatherReport(param.lat,param.lon)
        }else{
          repo.getWeatherReport(param.cityName)
        }

    }


}

data class WeatherReportParams(val cityName:String,val lat:Double,val lon:Double,val isForLocation:Boolean)