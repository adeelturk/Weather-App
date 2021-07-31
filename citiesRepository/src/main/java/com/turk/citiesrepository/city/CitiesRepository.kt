package com.turk.citiesrepository.city

import com.turk.common.base.BaseUseCase
import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.city.City
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {

     fun getAllCitiesList(url:String,isOnline: Boolean) :Flow<Either<ErrorEntity,List<City>>>
    fun searchCitiesByName(name: String): Flow<Either<ErrorEntity, List<City>>>
    fun changeCityFavouriteStatus(
        id: Int,
        favourite: Boolean
    ): Flow<Either<ErrorEntity, Int>>


}