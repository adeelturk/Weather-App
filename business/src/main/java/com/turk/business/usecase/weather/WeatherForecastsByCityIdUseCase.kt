package com.turk.business.usecase.weather

import com.turk.common.base.BaseUseCase
import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.weather.Weather
import com.turk.weatherrepository.weather.WeatherRepositoryImpl
import kotlinx.coroutines.flow.Flow

class WeatherForecastsByCityIdUseCase(private val repo: WeatherRepositoryImpl) :
    BaseUseCase<List<Weather>, WeatherForecastParams>() {


    override suspend fun run(param: WeatherForecastParams): Flow<Either<ErrorEntity, List<Weather>>> {

        return repo.getWeatherForecastReport(param.cityId)

    }


}

data class WeatherForecastParams(val cityId: Int)