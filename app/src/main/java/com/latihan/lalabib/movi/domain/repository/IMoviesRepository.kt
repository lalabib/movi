package com.latihan.lalabib.movi.domain.repository

import com.latihan.lalabib.movi.data.Resource
import com.latihan.lalabib.movi.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {

    fun getMovie(): Flow<Resource<List<Movies>>>

    fun getFavMovie(): Flow<List<Movies>>

    fun setFavoriteMovie(movie: Movies, state: Boolean)
}