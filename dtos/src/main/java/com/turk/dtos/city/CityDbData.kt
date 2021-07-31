package com.turk.dtos.city

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityDbData(@PrimaryKey val id:Int, val country:String, val name:String,
                      val lon: Double, val lat: Double, val population: Long, var isFavourite:Boolean=false
)
