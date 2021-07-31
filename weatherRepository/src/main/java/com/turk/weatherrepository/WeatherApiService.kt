package com.turk.weatherrepository

import com.turk.dtos.serverObjects.ForecastEntity
import com.turk.dtos.serverObjects.WeatherEntity
import com.turk.dtos.serverObjects.WeatherForecastEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiService {

    //api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
    @GET("weather")
    fun getWeatherReport(@Query("lat") lat: Double,@Query("lon") lon: Double,@Query("appid") apikey: String):
            Call<WeatherEntity>
    @GET("weather")
    fun getWeatherReport(@Query("q") cityName:String, @Query("appid") apikey: String):
            Call<WeatherEntity>

    @GET("forecast")
    fun getWeatherForecastReport(@Query("id") cityId:Int, @Query("appid") apikey: String):
            Call<ForecastEntity>
}
