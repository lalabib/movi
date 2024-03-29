package com.latihan.lalabib.movi.core.domain.usecase

import com.latihan.lalabib.movi.core.data.Resource
import com.latihan.lalabib.movi.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getMovie(): Flow<Resource<List<Movies>>>

    fun getFavMovie(): Flow<List<Movies>>

    fun searchMovie(query: String): Flow<List<Movies>>

    fun getMovieById(id: String): Flow<Movies>

    suspend fun setFavoriteMovie(movie: Movies, state: Boolean)
}