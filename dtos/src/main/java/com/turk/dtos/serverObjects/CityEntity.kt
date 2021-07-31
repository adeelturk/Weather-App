package com.turk.dtos.serverObjects

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityEntity(val id:Int, val country:String, val name:String,
                      val coord:Coordinates, val geoname:GeoName,val stat:Statistics) : Parcelable

@Parcelize
data class GeoName(val cl:String,val code:String,val parent:Long):Parcelable

@Parcelize
data class Statistics(val population:Long):Parcelable


