package com.latihan.lalabib.movi.di

import android.content.Context
import com.latihan.lalabib.movi.data.MoviRepository
import com.latihan.lalabib.movi.data.local.room.MoviDatabase
import com.latihan.lalabib.movi.data.remote.RemoteDataSource
import com.latihan.lalabib.movi.networking.ApiConfig

object Injection {

    fun provideRepository(context: Context): MoviRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        val database = MoviDatabase.getInstance(context)
        val apiService = ApiConfig.getApiService()
        return MoviRepository.getInstance(remoteDataSource, database, apiService)
    }
}