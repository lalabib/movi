package com.latihan.lalabib.movi.core.data.source.local

import com.latihan.lalabib.movi.core.data.source.local.entity.MoviesEntity
import com.latihan.lalabib.movi.core.data.source.local.room.MoviDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val moviDao: MoviDao) {

    fun getAllMovie(): Flow<List<MoviesEntity>> = moviDao.getAllMovie()

    suspend fun insertMovie(movie: List<MoviesEntity>) = moviDao.insertMovie(movie)

    fun setMovieStatus(movie: MoviesEntity, newState: Boolean) {
        movie.isFavorite = newState
        moviDao.updateMovie(movie)
    }

    fun getFavMovie():  Flow<List<MoviesEntity>> = moviDao.getFavoriteMovie()

}