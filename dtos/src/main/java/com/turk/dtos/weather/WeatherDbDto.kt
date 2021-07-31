package com.turk.dtos.weather

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherDbDto(
    @PrimaryKey()
    var id: Int = 0,
    val visibility: Long,
    val cityName: String,
    val cod: Int,
    val base: String,
    val timezone: Long,
    val couldData: Int,
    val weatherId: Int,
    val weatherType: Int,
    val country: String,
    val sunrise: Long,
    val sunset: Long,
    val windDeg: Int,
    val windSpeed: Double,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double,
)

//temp, humidity, wind,...
