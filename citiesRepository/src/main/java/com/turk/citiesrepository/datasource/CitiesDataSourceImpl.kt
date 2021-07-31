package com.turk.citiesrepository.datasource

import com.turk.citiesrepository.CitiesApiService
import com.turk.citiesrepository.city.CitiesRepositoryImpl
import com.turk.common.base.BaseUseCase
import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.city.City
import com.turk.dtos.mapper.CityDataMapperImpl
import com.turk.localpersistance.city.CitiesDao
import com.turk.network.requestBlockingFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CitiesDataSourceImpl(private val cityApiService: CitiesApiService,
                           private val mapper: CityDataMapperImpl,
                           private val citiesDao:CitiesDao,
) :
    CitiesDataSource {

    override fun getCities(url: String, isOnline: Boolean): Flow<Either<ErrorEntity, List<City>>> {

       citiesDao.getAllCitiesData.let {
           return if(it.isEmpty()){
               cityApiService.getCitiesData(url).requestBlockingFlow {dataList->

                   val cacheDataList= mapper.mapToDbModel(dataList).run {
                       citiesDao.insert(this)
                       this
                   }

                   mapper.mapToDomainModel(cacheDataList)
               }


           }else{
               flow { emit(Either.Right(mapper.mapToDomainModel(it))) }

           }
       }


    }


    override fun searchCitiesByName(name: String): Flow<Either<ErrorEntity, List<City>>> {
       return flow { emit(Either.Right( mapper.mapToDomainModel(citiesDao.searchCitiesByName(name)))) }
    }

    override fun changeCityFavouriteStatus(
        id: Int,
        isFavourite: Boolean
    ): Flow<Either<ErrorEntity, Int>> {

     return  flow{

           emit(
               Either.Right(  citiesDao.update(id,isFavourite))
           )
       }


    }
}