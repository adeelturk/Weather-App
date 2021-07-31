package com.turk.business.usecase.cities

import com.turk.citiesrepository.city.CitiesRepository
import com.turk.citiesrepository.city.CitiesRepositoryImpl
import com.turk.common.base.BaseUseCase
import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.city.City
import kotlinx.coroutines.flow.Flow

class FavouriteCityUseCase(private val citiesRepository: CitiesRepositoryImpl) :BaseUseCase<Int,FavouriteCityParams>() {


    override suspend fun run(param: FavouriteCityParams): Flow<Either<ErrorEntity, Int>> {

        return citiesRepository.changeCityFavouriteStatus(param.id,param.isFavourite)
    }


}

data class FavouriteCityParams(val id:Int,val isFavourite:Boolean)