package com.latihan.lalabib.movi.di

import com.latihan.lalabib.movi.core.domain.usecase.MoviesInteractor
import com.latihan.lalabib.movi.core.domain.usecase.MoviesUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideMoviesUceCase(moviesInteractor: MoviesInteractor): MoviesUseCase
}