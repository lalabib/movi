package com.latihan.lalabib.movi.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.latihan.lalabib.movi.data.local.entity.MoviesEntity
import com.latihan.lalabib.movi.data.local.entity.RemoteKeys

@Database(entities = [MoviesEntity::class, RemoteKeys::class], version = 1, exportSchema = false)
abstract class MoviDatabase: RoomDatabase() {

    abstract fun movieDao(): MoviDao
    abstract fun remoteKeyDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var instance: MoviDatabase? = null

        fun getInstance(context: Context): MoviDatabase = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                MoviDatabase::class.java, "Movi.db"
            ).build().also { instance = it }
        }
    }
}