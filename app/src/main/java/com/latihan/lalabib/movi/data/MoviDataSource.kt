package com.latihan.lalabib.movi.data

import androidx.lifecycle.LiveData
import com.latihan.lalabib.movi.data.local.entity.MoviesEntity
import com.latihan.lalabib.movi.utils.Resource

interface MoviDataSource {

    fun getDetailMovie(id: String): LiveData<Resource<MoviesEntity>>
}