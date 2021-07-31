package com.turk.business.usecase.cities

import com.turk.citiesrepository.city.CitiesRepository
import com.turk.citiesrepository.city.CitiesRepositoryImpl
import com.turk.common.base.BaseUseCase
import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.city.City
import kotlinx.coroutines.flow.Flow

class CitiesListUseCase(private val citiesRepository: CitiesRepositoryImpl) :BaseUseCase<List<City>,CitiesListParams>() {


    override suspend fun run(param: CitiesListParams): Flow<Either<ErrorEntity, List<City>>> {

        return citiesRepository.getAllCitiesList(param.url,param.isOnline)
    }


}

data class CitiesListParams(val url:String,val isOnline:Boolean)