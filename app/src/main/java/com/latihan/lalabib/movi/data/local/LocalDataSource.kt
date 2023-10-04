package com.latihan.lalabib.movi.data.local

import androidx.lifecycle.LiveData
import com.latihan.lalabib.movi.data.local.entity.MoviesEntity
import com.latihan.lalabib.movi.data.local.room.MoviDao

class LocalDataSource(private val moviDao: MoviDao) {

    fun getAllMovie(): LiveData<List<MoviesEntity>>  = moviDao.getAllMovie()

    fun insertMovie(movie: List<MoviesEntity>) = moviDao.insertMovie(movie)

    fun getDetailMovie(id: String): LiveData<MoviesEntity> = moviDao.getDetailMovie(id)

    fun updateMovie(movie: MoviesEntity) = moviDao.updateMovie(movie)

    fun setMovieStatus(movie: MoviesEntity, newState: Boolean) {
        movie.isFavorite = newState
        moviDao.updateMovie(movie)
    }

    fun getFavMovie(): LiveData<List<MoviesEntity>> = moviDao.getFavoriteMovie()

    companion object {
        @Volatile
        private var instance: LocalDataSource? = null

        fun getInstance(moviDao: MoviDao): LocalDataSource =
            instance ?: LocalDataSource(moviDao)
    }
}