package com.latihan.lalabib.movi.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entities")
data class MoviesEntity(

    @PrimaryKey
    val id: String,

    val title: String,

    val overview: String,

    @ColumnInfo(name = "release_date")
    val release_date: String,

    @ColumnInfo(name = "vote_average")
    val vote_average: String,

    @ColumnInfo(name = "poster_path")
    val poster_path: String,
)