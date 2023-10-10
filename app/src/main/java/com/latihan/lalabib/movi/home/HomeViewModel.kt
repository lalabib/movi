package com.latihan.lalabib.movi.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.latihan.lalabib.movi.core.domain.model.Movies
import com.latihan.lalabib.movi.core.domain.usecase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(moviesUseCase: MoviesUseCase): ViewModel() {

    val movie = moviesUseCase.getMovie().asLiveData()

    val searchMovie: (String) -> LiveData<List<Movies>> = { query ->
        moviesUseCase.searchMovie(query).asLiveData()
    }
}