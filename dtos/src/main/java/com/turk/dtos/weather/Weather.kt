package com.turk.dtos.weather

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather( var id: Int = 0,
                    val visibility: Long,
                    val cityName: String,
                    val countryCode: String,
                    val windDeg: Int,
                    val windSpeed: Double,
                    val feels_like: Double,
                    val humidity: Int,
                    val pressure: Int,
                    val temp: Double,
                    val temp_max: Double,
                    val temp_min: Double,
): Parcelable
