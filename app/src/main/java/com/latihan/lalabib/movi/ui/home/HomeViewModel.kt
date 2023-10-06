package com.latihan.lalabib.movi.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.latihan.lalabib.movi.domain.usecase.MoviesUseCase

class HomeViewModel(moviesUseCase: MoviesUseCase): ViewModel() {

    val movie = moviesUseCase.getMovie().asLiveData()
}