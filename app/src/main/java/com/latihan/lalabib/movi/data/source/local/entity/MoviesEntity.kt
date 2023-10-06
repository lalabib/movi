package com.latihan.lalabib.movi.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_entities")
data class MoviesEntity(
    @PrimaryKey
    val id: String,

    val title: String,

    val overview: String,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage: String,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    var isFavorite: Boolean = false
)