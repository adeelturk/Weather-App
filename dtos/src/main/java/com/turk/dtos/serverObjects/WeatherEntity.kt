package com.turk.dtos.serverObjects

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherEntity(
    val id: Int,
    val visibility: Long,
    val name: String,
    val cod: Int,
    val base: String,
    val timezone: Long,
    val clouds: Cloud,
    val coord: Coordinates,
    val main: Main,
    val wind: Wind,
    val sys: WeatherSystemData,
    ) : Parcelable


@Parcelize
data class WeatherSystemData(
    val id: Int,
    val type: Int,
    val country: String,
    val sunrise: Long,
    val sunset: Long,
) : Parcelable

@Parcelize
data class Wind(val deg: Int, val speed: Double) : Parcelable

@Parcelize
data class Cloud(val all: Int) : Parcelable

@Parcelize
data class Main(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double,


    ) : Parcelable