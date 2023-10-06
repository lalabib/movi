package com.latihan.lalabib.movi.di

import android.content.Context
import com.latihan.lalabib.movi.data.MoviRepository
import com.latihan.lalabib.movi.data.source.local.LocalDataSource
import com.latihan.lalabib.movi.data.source.local.room.MoviDatabase
import com.latihan.lalabib.movi.data.source.remote.RemoteDataSource
import com.latihan.lalabib.movi.data.source.remote.network.ApiConfig
import com.latihan.lalabib.movi.domain.repository.IMoviesRepository
import com.latihan.lalabib.movi.domain.usecase.MoviesInteractor
import com.latihan.lalabib.movi.domain.usecase.MoviesUseCase
import com.latihan.lalabib.movi.utils.AppExecutors

object Injection {

    private fun provideRepository(context: Context): IMoviesRepository {

        val database = MoviDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.getApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MoviRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideMoviesUseCase(context: Context): MoviesUseCase {
        val repository = provideRepository(context)
        return MoviesInteractor(repository)
    }
}