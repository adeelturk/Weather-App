package com.turk.dtos.serverObjects

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coordinates(val lon: Double, val lat: Double):Parcelable