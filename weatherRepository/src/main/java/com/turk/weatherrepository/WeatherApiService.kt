package com.turk.weatherrepository

import com.turk.dtos.serverObjects.WeatherEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiService {

    //api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
    @GET("/weather")
    fun getWeatherReport(@Query("lat") lat: Double,@Query("lon") lon: Double,@Query("appid") apikey: String):
            Call<WeatherEntity>
    @GET("/weather/{q}")
    fun getWeatherReport(@Path("q") cityName:String, @Query("appid") apikey: String):
            Call<WeatherEntity>
}


//@GET("/api/searchtypes/{Id}/filters")
//Call<FilterResponse> getFilterList(
//@Path("Id") long customerId,
//@Query("Type") String responseType,
//@Query("SearchText") String searchText
//);