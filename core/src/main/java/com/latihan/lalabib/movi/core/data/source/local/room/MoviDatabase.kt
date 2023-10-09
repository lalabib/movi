package com.latihan.lalabib.movi.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.latihan.lalabib.movi.core.data.source.local.entity.MoviesEntity

@Database(entities = [MoviesEntity::class], version = 1, exportSchema = false)
abstract class MoviDatabase : RoomDatabase() {

    abstract fun movieDao(): MoviDao
}