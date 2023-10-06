package com.latihan.lalabib.movi.domain.usecase

import androidx.lifecycle.LiveData
import com.latihan.lalabib.movi.data.Resource
import com.latihan.lalabib.movi.domain.model.Movies

interface MoviesUseCase {
    fun getMovie(): LiveData<Resource<List<Movies>>>

    fun getFavMovie(): LiveData<List<Movies>>

    fun setFavoriteMovie(movie: Movies, state: Boolean)
}