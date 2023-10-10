package com.latihan.lalabib.movi.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.latihan.lalabib.movi.core.domain.model.Movies
import com.latihan.lalabib.movi.core.domain.usecase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MoviesUseCase) : ViewModel() {

    fun setFavoriteMovies(movies: Movies, newState: Boolean) {
        viewModelScope.launch {
            movieUseCase.setFavoriteMovie(movies, newState)
        }
    }

    val detailMovie: (String) -> LiveData<Movies> = { movieId ->
        movieUseCase.getMovieById(movieId).asLiveData()
    }
}