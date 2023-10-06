package com.latihan.lalabib.movi.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results")
    val results: ArrayList<DetailMovieResponse>
)