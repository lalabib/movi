package com.latihan.lalabib.movi.data.local

import androidx.lifecycle.LiveData
import com.latihan.lalabib.movi.data.local.entity.MoviesEntity
import com.latihan.lalabib.movi.data.local.room.MoviDao

class LocalDataSource(private val moviDao: MoviDao) {

    fun getDetailMovie(id: String): LiveData<MoviesEntity> = moviDao.getDetailMovie(id)

    fun updateMovie(movie: MoviesEntity) = moviDao.updateMovie(movie)

    companion object {
        @Volatile
        private var instance: LocalDataSource? = null

        fun getInstance(moviDao: MoviDao): LocalDataSource =
            instance ?: LocalDataSource(moviDao)
    }
}