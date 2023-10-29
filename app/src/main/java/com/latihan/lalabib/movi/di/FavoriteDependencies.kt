package com.latihan.lalabib.movi.di

import com.latihan.lalabib.movi.core.domain.usecase.MoviesUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteDependencies {
    fun moviesUseCase(): MoviesUseCase
}