package com.latihan.lalabib.movi.core.di

import android.content.Context
import androidx.room.Room
import com.latihan.lalabib.movi.core.data.source.local.room.MoviDao
import com.latihan.lalabib.movi.core.data.source.local.room.MoviDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MoviDatabase = Room.databaseBuilder(
        context,
        MoviDatabase::class.java, "Movi.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideMovieDao(database: MoviDatabase): MoviDao = database.movieDao()
}