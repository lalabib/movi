package com.latihan.lalabib.movi.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.latihan.lalabib.movi.data.MoviRepository
import com.latihan.lalabib.movi.data.local.entity.MoviesEntity
import com.latihan.lalabib.movi.utils.Resource

class DetailViewModel(private val repository: MoviRepository): ViewModel() {

    private val movieId = MutableLiveData<String>()

    fun setMoviesData(id: String) {
        movieId.value = id
    }

    var detailMovie: LiveData<Resource<MoviesEntity>> =
        Transformations.switchMap(movieId) { movieId ->
            repository.getDetailMovie(movieId)
        }
}