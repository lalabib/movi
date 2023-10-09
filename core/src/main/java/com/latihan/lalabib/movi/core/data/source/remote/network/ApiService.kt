package com.latihan.lalabib.movi.core.data.source.remote.network

import com.latihan.lalabib.movi.core.BuildConfig.apiKey
import com.latihan.lalabib.movi.core.data.source.remote.response.MoviesResponse
import retrofit2.http.GET

interface ApiService {

    @GET("movie/popular?api_key=$apiKey")
    suspend fun getMovie(): MoviesResponse
}