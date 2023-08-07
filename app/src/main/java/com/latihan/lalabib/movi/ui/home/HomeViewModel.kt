package com.latihan.lalabib.movi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.latihan.lalabib.movi.BuildConfig
import com.latihan.lalabib.movi.data.MoviRepository
import com.latihan.lalabib.movi.data.remote.response.MoviesResponse

class HomeViewModel(private val repository: MoviRepository): ViewModel() {

    fun getMovies() : LiveData<MoviesResponse> = repository.getMovie(apiKey)

    companion object {
        private const val apiKey = BuildConfig.apiKey
    }
}