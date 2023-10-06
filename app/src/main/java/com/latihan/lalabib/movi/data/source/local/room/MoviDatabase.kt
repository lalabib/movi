package com.latihan.lalabib.movi.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.latihan.lalabib.movi.data.source.local.entity.MoviesEntity

@Database(entities = [MoviesEntity::class], version = 1, exportSchema = false)
abstract class MoviDatabase : RoomDatabase() {

    abstract fun movieDao(): MoviDao

    companion object {
        @Volatile
        private var instance: MoviDatabase? = null

        fun getInstance(context: Context): MoviDatabase = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                MoviDatabase::class.java, "Movi.db"
            )
                .fallbackToDestructiveMigration()
                .build()
                .also { instance = it }
        }
    }
}