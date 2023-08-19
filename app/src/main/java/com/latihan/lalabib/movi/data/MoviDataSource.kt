package com.latihan.lalabib.movi.data

import androidx.lifecycle.LiveData
import com.latihan.lalabib.movi.data.remote.response.DetailMovieResponse
import com.latihan.lalabib.movi.data.remote.response.MoviesResponse
import com.latihan.lalabib.movi.utils.Resource

interface MoviDataSource {

    fun getDetailMovie(id: String): LiveData<DetailMovieResponse>
}