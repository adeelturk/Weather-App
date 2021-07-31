package com.turk.citiesrepository

import com.turk.dtos.serverObjects.CityEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface CitiesApiService {

    @GET
     fun getCitiesData(@Url path:String): Call<List<CityEntity>>
}