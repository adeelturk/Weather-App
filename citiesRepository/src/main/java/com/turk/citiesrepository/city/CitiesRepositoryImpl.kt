package com.turk.citiesrepository.city

import com.turk.citiesrepository.CitiesApiService
import com.turk.citiesrepository.datasource.CitiesDataSourceImpl
import com.turk.common.base.BaseUseCase
import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.city.City
import com.turk.dtos.mapper.CityDataMapperImpl
import com.turk.network.requestBlockingFlow
import kotlinx.coroutines.flow.Flow

class  CitiesRepositoryImpl(private val citiesDataSourceImpl: CitiesDataSourceImpl
):CitiesRepository {

    override fun searchCitiesByName(name: String): Flow<Either<ErrorEntity, List<City>>> {
        return citiesDataSourceImpl.searchCitiesByName(name)
    }

    override fun getAllCitiesList(url:String, isOnline: Boolean) :Flow<Either<ErrorEntity,List<City>>>{

       return citiesDataSourceImpl.getCities(url,isOnline)
    }

    override fun changeCityFavouriteStatus(
        id: Int,
        favourite: Boolean
    ): Flow<Either<ErrorEntity, Int>> {

        return citiesDataSourceImpl.changeCityFavouriteStatus(id,favourite)
    }
}