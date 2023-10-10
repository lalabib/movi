package com.latihan.lalabib.movi.core.data.source.local

import com.latihan.lalabib.movi.core.data.source.local.entity.MoviesEntity
import com.latihan.lalabib.movi.core.data.source.local.room.MoviDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val moviDao: MoviDao) {

    fun getAllMovie(): Flow<List<MoviesEntity>> = moviDao.getAllMovie()

    fun searchMovie(query: String) = moviDao.searchMovie(query)

    fun getMovieById(id: String) = moviDao.getMovieById(id)

    suspend fun insertMovie(movie: List<MoviesEntity>) = moviDao.insertMovie(movie)

    suspend fun setMovieStatus(movie: MoviesEntity, newState: Boolean) {
        movie.isFavorite = newState
        moviDao.updateMovie(movie)
    }

    fun getFavMovie():  Flow<List<MoviesEntity>> = moviDao.getFavoriteMovie()
}