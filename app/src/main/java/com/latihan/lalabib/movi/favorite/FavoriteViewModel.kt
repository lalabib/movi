package com.latihan.lalabib.movi.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.latihan.lalabib.movi.core.domain.usecase.MoviesUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(moviesUseCase: MoviesUseCase): ViewModel() {

    val favoriteMovie = moviesUseCase.getFavMovie().asLiveData()
}