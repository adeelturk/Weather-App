package com.turk.localpersistance


import androidx.room.Database
import androidx.room.RoomDatabase
import com.turk.dtos.city.CityDbData
import com.turk.localpersistance.city.CitiesDao

@Database(entities = [CityDbData::class], version = 1, exportSchema = false)
abstract class AppLocalDatabase: RoomDatabase() {
    abstract val citiesDao: CitiesDao?
}