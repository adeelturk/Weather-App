package com.turk.citiesrepository

import com.turk.citiesrepository.city.CitiesRepositoryImpl
import com.turk.citiesrepository.datasource.CitiesDataSourceImpl
import com.turk.common.livedata.ConnectionLiveData
import com.turk.dtos.mapper.CityDataMapperImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val citiesRepoDependencies= module {

    //retrofit
    single { get<Retrofit>().create(CitiesApiService::class.java) }

    // Dto Mapper
    single { CityDataMapperImpl() }

    single {ConnectionLiveData(get()) }
    //dataSource
    single { CitiesDataSourceImpl(get(),get(),get())}



    //repositories
    single { CitiesRepositoryImpl(get()) }


}

