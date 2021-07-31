package com.turk.business.usecase.weather

import com.turk.common.base.BaseUseCase
import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.const.WeatherUnit
import com.turk.dtos.weather.Weather
import com.turk.dtos.weather.WeatherReport
import com.turk.weatherrepository.weather.WeatherRepositoryImpl
import kotlinx.coroutines.flow.Flow

class ChangeTemperatureUnitUseCase(private val repo: WeatherRepositoryImpl) :
    BaseUseCase<WeatherReport, ChangeTemperatureUnitUseCaseParams>() {


    override suspend fun run(param: ChangeTemperatureUnitUseCaseParams): Flow<Either<ErrorEntity, WeatherReport>> {

        return when(param.unit){
            WeatherUnit.CELCIUS->{
                repo.getWeatherDatInClecius(WeatherReport(param.currentCityWeather,param.foreCast))
            }

            WeatherUnit.KELVIN->{
                repo.getWeatherDatInKelivin(WeatherReport(param.currentCityWeather,param.foreCast))
            }
        }


    }


}

data class ChangeTemperatureUnitUseCaseParams(val currentCityWeather:Weather, val foreCast: List<Weather>,val unit:WeatherUnit)