package com.latihan.lalabib.movi.core.data

import com.latihan.lalabib.movi.core.data.source.local.LocalDataSource
import com.latihan.lalabib.movi.core.data.source.remote.RemoteDataSource
import com.latihan.lalabib.movi.core.data.source.remote.network.ApiResponse
import com.latihan.lalabib.movi.core.data.source.remote.response.DetailMovieResponse
import com.latihan.lalabib.movi.core.domain.model.Movies
import com.latihan.lalabib.movi.core.domain.repository.IMoviesRepository
import com.latihan.lalabib.movi.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IMoviesRepository {

    override fun getMovie(): Flow<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<DetailMovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movies>> {
                return localDataSource.getAllMovie().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<DetailMovieResponse>>> =
                remoteDataSource.getMovie()

            override suspend fun saveCallResult(data: List<DetailMovieResponse>) {
                val moviesList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMovie(moviesList)
            }
        }.asFlow()

    override fun getFavMovie(): Flow<List<Movies>> {
        return localDataSource.getFavMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun searchMovie(query: String): Flow<List<Movies>> =
        localDataSource.searchMovie(query).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    override fun getMovieById(id: String): Flow<Movies> =
        localDataSource.getMovieById(id).map {
            DataMapper.mapEntitiesToDomain(it)
        }

    override suspend fun setFavoriteMovie(movie: Movies, state: Boolean) {
        val moviesEntity = DataMapper.mapDomainToEntity(movie)
        localDataSource.setMovieStatus(moviesEntity, state)
    }

}