package com.latihan.lalabib.movi.data.remote.response

import com.google.gson.annotations.SerializedName
import com.latihan.lalabib.movi.data.local.MoviesEntity

data class MoviesResponse(
    @SerializedName("results")
    val results: ArrayList<MoviesEntity>
)