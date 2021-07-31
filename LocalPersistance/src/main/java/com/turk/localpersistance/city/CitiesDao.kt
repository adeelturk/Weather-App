package com.turk.localpersistance.city

import androidx.room.Dao
import androidx.room.Query
import com.turk.dtos.city.CityDbData
import com.turk.localpersistance.BaseDao

@Dao
interface CitiesDao:BaseDao<CityDbData> {
    @Query("SELECT * FROM CityDbData WHERE name LIKE '%' || :search || '%'")
    fun searchCitiesByName(search: String):  List<CityDbData>

    @get:Query("SELECT * FROM CityDbData")
    val getAllCitiesData: List<CityDbData>

    @Query("UPDATE CityDbData SET isFavourite=:isFavourite WHERE id = :id")
    fun update( id: Int,isFavourite:Boolean):Int

}