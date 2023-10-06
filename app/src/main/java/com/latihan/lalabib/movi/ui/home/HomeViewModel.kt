package com.latihan.lalabib.movi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.latihan.lalabib.movi.domain.model.Movies
import com.latihan.lalabib.movi.data.Resource
import com.latihan.lalabib.movi.domain.usecase.MoviesUseCase

class HomeViewModel(moviesUseCase: MoviesUseCase): ViewModel() {

    val movie: LiveData<Resource<List<Movies>>> = moviesUseCase.getMovie()
}