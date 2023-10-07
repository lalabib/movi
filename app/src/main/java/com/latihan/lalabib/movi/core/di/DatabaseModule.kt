package com.latihan.lalabib.movi.core.di

import android.content.Context
import androidx.room.Room
import com.latihan.lalabib.movi.core.data.source.local.room.MoviDao
import com.latihan.lalabib.movi.core.data.source.local.room.MoviDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): MoviDatabase = Room.databaseBuilder(
        context,
        MoviDatabase::class.java, "Movi.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideMovieDao(database: MoviDatabase): MoviDao = database.movieDao()
}