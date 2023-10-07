package com.latihan.lalabib.movi.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.latihan.lalabib.movi.core.domain.usecase.MoviesUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(moviesUseCase: MoviesUseCase): ViewModel() {

    val movie = moviesUseCase.getMovie().asLiveData()
}