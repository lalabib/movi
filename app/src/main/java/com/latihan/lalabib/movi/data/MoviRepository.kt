package com.latihan.lalabib.movi.data

import com.latihan.lalabib.movi.data.source.local.LocalDataSource
import com.latihan.lalabib.movi.data.source.remote.response.DetailMovieResponse
import com.latihan.lalabib.movi.data.source.remote.network.ApiResponse
import com.latihan.lalabib.movi.data.source.remote.RemoteDataSource
import com.latihan.lalabib.movi.domain.model.Movies
import com.latihan.lalabib.movi.domain.repository.IMoviesRepository
import com.latihan.lalabib.movi.utils.AppExecutors
import com.latihan.lalabib.movi.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
): IMoviesRepository {

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

    override fun setFavoriteMovie(movie: Movies, state: Boolean) {
        val moviesEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.setMovieStatus(moviesEntity, state)
        }
    }

    companion object {
        @Volatile
        private var instance: MoviRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors,
        ): MoviRepository = instance ?: synchronized(this) {
            instance ?: MoviRepository(
                remoteDataSource,
                localDataSource,
                appExecutors,
            )
        }
    }
}