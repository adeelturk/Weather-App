package com.turk.dtos.serverObjects

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForecastEntity(
    val list:List<WeatherForecastEntity>,
    val city:RequestedCityData
    ) : Parcelable


@Parcelize
data class RequestedCityData(val id:Int, val country:String, val name:String,
                      val coord:Coordinates) : Parcelable


@Parcelize
data class WeatherForecastEntity(
    val dt: Int,
    val dt_txt: String,
    val visibility: Long,
    val main: Main,
    val wind: Wind,
    ) : Parcelable


