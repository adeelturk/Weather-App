package com.turk.business.usecase.cities

import com.turk.citiesrepository.city.CitiesRepository
import com.turk.citiesrepository.city.CitiesRepositoryImpl
import com.turk.common.base.BaseUseCase
import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.city.City
import kotlinx.coroutines.flow.Flow

class SearchCityByName(private val citiesRepository: CitiesRepositoryImpl) :BaseUseCase<List<City>,SearchCityByNameParams>() {


    override suspend fun run(param: SearchCityByNameParams): Flow<Either<ErrorEntity, List<City>>> {

        return citiesRepository.searchCitiesByName(param.name)
    }


}

data class SearchCityByNameParams(val name:String)