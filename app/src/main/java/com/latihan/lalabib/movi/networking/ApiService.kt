package com.latihan.lalabib.movi.networking

import com.latihan.lalabib.movi.data.remote.response.DetailMovieResponse
import com.latihan.lalabib.movi.data.remote.response.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovie(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): MoviesResponse

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): Call<DetailMovieResponse>
}