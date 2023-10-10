package com.latihan.lalabib.movi.core.domain.model

import com.google.gson.annotations.SerializedName

data class Movies (
    val id: String,

    val title: String,

    val overview: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("vote_average")
    val voteAverage: String,

    @SerializedName("poster_path")
    val posterPath: String,

    var isFavorite: Boolean = false
)