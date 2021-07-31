package com.turk.localpersistance

import androidx.room.*

@Dao
interface BaseDao<T>{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<T>)

    @Delete
    fun delete(entity: T)

    @Delete
    fun delete(entities: List<T>)

    @Update
    fun update( data: T)
}