package com.latihan.lalabib.movi.data.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results")
    val results: ArrayList<DetailMovieResponse>
)