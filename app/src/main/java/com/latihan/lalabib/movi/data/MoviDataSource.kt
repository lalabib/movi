package com.latihan.lalabib.movi.data

import androidx.lifecycle.LiveData
import com.latihan.lalabib.movi.data.remote.response.MoviesResponse

interface MoviDataSource {

    fun getMovie(apiKey: String) : LiveData<MoviesResponse>
}