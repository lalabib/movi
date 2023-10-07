package com.latihan.lalabib.movi.core.di

import com.latihan.lalabib.movi.core.data.MoviRepository
import com.latihan.lalabib.movi.core.domain.repository.IMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(moviRepository: MoviRepository): IMoviesRepository
}