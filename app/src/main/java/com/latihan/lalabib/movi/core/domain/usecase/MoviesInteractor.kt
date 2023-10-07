package com.latihan.lalabib.movi.core.domain.usecase

import com.latihan.lalabib.movi.core.domain.model.Movies
import com.latihan.lalabib.movi.core.domain.repository.IMoviesRepository
import javax.inject.Inject


class MoviesInteractor @Inject constructor(private val moviRepository: IMoviesRepository) : MoviesUseCase {
    override fun getMovie() = moviRepository.getMovie()


    override fun getFavMovie() = moviRepository.getFavMovie()

    override fun setFavoriteMovie(movie: Movies, state: Boolean) =
        moviRepository.setFavoriteMovie(movie, state)
}