package com.latihan.lalabib.movi.detail

import androidx.lifecycle.ViewModel
import com.latihan.lalabib.movi.core.domain.model.Movies
import com.latihan.lalabib.movi.core.domain.usecase.MoviesUseCase

class DetailViewModel(private val movieUseCase: MoviesUseCase): ViewModel() {

    fun setFavoriteMovies(movies: Movies, newState: Boolean) = movieUseCase.setFavoriteMovie(movies, newState)
}