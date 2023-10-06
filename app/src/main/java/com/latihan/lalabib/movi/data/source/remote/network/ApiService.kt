package com.latihan.lalabib.movi.data.source.remote.network

import com.latihan.lalabib.movi.data.source.remote.response.MoviesResponse
import com.latihan.lalabib.movi.BuildConfig.apiKey
import retrofit2.http.GET

interface ApiService {

    @GET("movie/popular?api_key=$apiKey")
    suspend fun getMovie(): MoviesResponse
}