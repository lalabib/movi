package com.latihan.lalabib.movi.ui.detail

import androidx.lifecycle.ViewModel
import com.latihan.lalabib.movi.domain.model.Movies
import com.latihan.lalabib.movi.domain.usecase.MoviesUseCase

class DetailViewModel(private val movieUseCase: MoviesUseCase): ViewModel() {

    fun setFavoriteMovies(movies: Movies, newState: Boolean) = movieUseCase.setFavoriteMovie(movies, newState)
}