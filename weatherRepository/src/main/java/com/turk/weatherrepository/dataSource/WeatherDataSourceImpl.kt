package com.turk.weatherrepository.dataSource


import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.mapper.WeatherDataMapper
import com.turk.dtos.serverObjects.WeatherEntity
import com.turk.dtos.serverObjects.WeatherForecastEntity
import com.turk.dtos.weather.Weather
import com.turk.dtos.weather.WeatherReport
import com.turk.network.requestBlockingFlow
import com.turk.weatherrepository.WeatherApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

class WeatherDataSourceImpl(private val apisService:WeatherApiService,
                            private val mapper:WeatherDataMapper,
                            private val apikey:String
                           ) :WeatherDataSource{


   override fun getWeatherReport(lat:Double,lon:Double):Flow<Either<ErrorEntity, Weather>>{

       return apisService.getWeatherReport(lat=lat,lon=lon,apikey = apikey).requestBlockingFlow {
         val cacheData =  mapper.mapToDbModel(it)
          mapper.mapToDomainModel(cacheData)

        }

    }

    override fun getWeatherReport(cityName:String):Flow<Either<ErrorEntity,Weather>>{

        return apisService.getWeatherReport(cityName=cityName,apikey = apikey).requestBlockingFlow {
            val cacheData =  mapper.mapToDbModel(it)
            mapper.mapToDomainModel(cacheData)

        }
    }

    override fun getWeatherForecastReport(cityId: Int): Flow<Either<ErrorEntity, List<Weather>>> {

        return apisService.getWeatherForecastReport(cityId = cityId,apikey).requestBlockingFlow {
             mapper.mapToDomainModel(it.city,it.list)
        }
    }


    override fun getWeatherDatInClecius(data: WeatherReport): Flow<Either<ErrorEntity, WeatherReport>> {

        return flow { emit(Either.Right(mapper.toCelcis(data))) }
    }

    override fun getWeatherDatInKelivin(data: WeatherReport): Flow<Either<ErrorEntity, WeatherReport>> {
        return flow { emit(Either.Right(mapper.toKelvin(data))) }
    }


}