package com.latihan.lalabib.movi.data.source.local

import com.latihan.lalabib.movi.data.source.local.entity.MoviesEntity
import com.latihan.lalabib.movi.data.source.local.room.MoviDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val moviDao: MoviDao) {

    fun getAllMovie(): Flow<List<MoviesEntity>> = moviDao.getAllMovie()

    suspend fun insertMovie(movie: List<MoviesEntity>) = moviDao.insertMovie(movie)

    fun setMovieStatus(movie: MoviesEntity, newState: Boolean) {
        movie.isFavorite = newState
        moviDao.updateMovie(movie)
    }

    fun getFavMovie():  Flow<List<MoviesEntity>> = moviDao.getFavoriteMovie()

    companion object {
        @Volatile
        private var instance: LocalDataSource? = null

        fun getInstance(moviDao: MoviDao): LocalDataSource =
            instance ?: LocalDataSource(moviDao)
    }
}