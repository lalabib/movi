package com.latihan.lalabib.movi.di

import com.latihan.lalabib.movi.core.data.MoviRepository
import com.latihan.lalabib.movi.core.domain.usecase.MoviesInteractor
import com.latihan.lalabib.movi.core.domain.usecase.MoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoviesUceCase(repository: MoviRepository): MoviesUseCase =
        MoviesInteractor(repository)
}