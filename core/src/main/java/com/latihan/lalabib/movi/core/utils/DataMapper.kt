package com.latihan.lalabib.movi.core.utils

import com.latihan.lalabib.movi.core.data.source.local.entity.MoviesEntity
import com.latihan.lalabib.movi.core.data.source.remote.response.DetailMovieResponse
import com.latihan.lalabib.movi.core.domain.model.Movies

object DataMapper {
    fun mapResponseToEntities(input: List<DetailMovieResponse>): List<MoviesEntity> {
        val movieList = ArrayList<MoviesEntity>()
        input.map {
            val movies = MoviesEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                posterPath = it.posterPath,
            )
            movieList.add(movies)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MoviesEntity>): List<Movies> =
        input.map {
            Movies(
                id = it.id,
                title = it.title,
                overview = it.overview,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                posterPath = it.posterPath,
                isFavorite = it.isFavorite
            )
        }

    fun mapEntitiesToDomain(input: MoviesEntity): Movies {
        return Movies(
            id = input.id,
            title = input.title,
            overview = input.overview,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            posterPath = input.posterPath,
            isFavorite = input.isFavorite
        )
    }

    fun mapDomainToEntity(input: Movies) = MoviesEntity(
        id = input.id,
        title = input.title,
        overview = input.overview,
        releaseDate = input.releaseDate,
        voteAverage = input.voteAverage,
        posterPath = input.posterPath,
        isFavorite = input.isFavorite
    )
}