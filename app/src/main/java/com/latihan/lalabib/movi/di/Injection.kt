package com.latihan.lalabib.movi.di

import android.content.Context
import com.latihan.lalabib.movi.data.MoviRepository
import com.latihan.lalabib.movi.data.local.LocalDataSource
import com.latihan.lalabib.movi.data.local.room.MoviDatabase
import com.latihan.lalabib.movi.data.remote.RemoteDataSource
import com.latihan.lalabib.movi.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): MoviRepository {

        val database = MoviDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MoviRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}