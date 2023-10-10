package com.latihan.lalabib.movi.core.domain.repository

import com.latihan.lalabib.movi.core.data.Resource
import com.latihan.lalabib.movi.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {

    fun getMovie(): Flow<Resource<List<Movies>>>

    fun getFavMovie(): Flow<List<Movies>>

    fun setFavoriteMovie(movie: Movies, state: Boolean)
}