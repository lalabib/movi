package com.latihan.lalabib.movi.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.latihan.lalabib.movi.domain.model.Movies
import com.latihan.lalabib.movi.domain.usecase.MoviesUseCase

class FavoriteViewModel(moviesUseCase: MoviesUseCase): ViewModel() {

    val favoriteMovie: LiveData<List<Movies>> = moviesUseCase.getFavMovie()
}