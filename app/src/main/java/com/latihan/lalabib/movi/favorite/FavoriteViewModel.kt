package com.latihan.lalabib.movi.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.latihan.lalabib.movi.core.domain.usecase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(moviesUseCase: MoviesUseCase): ViewModel() {

    val favoriteMovie = moviesUseCase.getFavMovie().asLiveData()
}