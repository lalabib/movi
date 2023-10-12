package com.latihan.lalabib.movi.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesResponse(
    @SerializedName("results")
    val results: ArrayList<DetailMovieResponse>
):Parcelable