package com.turk.localpersistance

import android.content.Context

import androidx.room.Room


class RoomSupport(context: Context) {

    private val DB_NAME = "weatherApp.db"
    private val database: AppLocalDatabase = Room.databaseBuilder(context.applicationContext,
            AppLocalDatabase::class.java, DB_NAME)
            .allowMainThreadQueries().build()

    fun getCitiesDao()= database.citiesDao

}