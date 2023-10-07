package com.latihan.lalabib.movi.di

import com.latihan.lalabib.movi.core.domain.usecase.MoviesInteractor
import com.latihan.lalabib.movi.core.domain.usecase.MoviesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideMoviesUceCase(moviesInteractor: MoviesInteractor): MoviesUseCase
}