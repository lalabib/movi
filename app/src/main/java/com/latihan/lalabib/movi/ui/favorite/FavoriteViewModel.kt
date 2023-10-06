package com.latihan.lalabib.movi.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.latihan.lalabib.movi.domain.usecase.MoviesUseCase

class FavoriteViewModel(moviesUseCase: MoviesUseCase): ViewModel() {

    val favoriteMovie = moviesUseCase.getFavMovie().asLiveData()
}