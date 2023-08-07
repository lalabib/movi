package com.latihan.lalabib.movi.di

import com.latihan.lalabib.movi.data.MoviRepository
import com.latihan.lalabib.movi.data.remote.RemoteDataSource

object Injection {

    fun provideRepository(): MoviRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MoviRepository.getInstance(remoteDataSource)
    }
}