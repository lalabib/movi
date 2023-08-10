package com.latihan.lalabib.movi.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.latihan.lalabib.movi.data.MoviRepository
import com.latihan.lalabib.movi.data.remote.response.DetailMovieResponse

class DetailViewModel(private val repository: MoviRepository): ViewModel() {

    private val movieId = MutableLiveData<String>()

    fun setMoviesData(id: String) {
        movieId.value = id
    }

    var detailMovie: LiveData<DetailMovieResponse> =
        Transformations.switchMap(movieId) { movieId ->
            repository.getDetailMovie(movieId)
        }
}