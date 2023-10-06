package com.latihan.lalabib.movi.domain.usecase

import com.latihan.lalabib.movi.domain.model.Movies
import com.latihan.lalabib.movi.domain.repository.IMoviesRepository


class MoviesInteractor(private val moviRepository: IMoviesRepository) : MoviesUseCase {
    override fun getMovie() = moviRepository.getMovie()


    override fun getFavMovie() = moviRepository.getFavMovie()

    override fun setFavoriteMovie(movie: Movies, state: Boolean) =
        moviRepository.setFavoriteMovie(movie, state)
}