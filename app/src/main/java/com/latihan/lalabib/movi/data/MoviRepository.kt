package com.latihan.lalabib.movi.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.latihan.lalabib.movi.data.local.LocalDataSource
import com.latihan.lalabib.movi.data.remote.network.ApiResponse
import com.latihan.lalabib.movi.data.remote.RemoteDataSource
import com.latihan.lalabib.movi.data.remote.response.MoviesResponse
import com.latihan.lalabib.movi.domain.model.Movies
import com.latihan.lalabib.movi.domain.repository.IMoviesRepository
import com.latihan.lalabib.movi.utils.AppExecutors
import com.latihan.lalabib.movi.utils.DataMapper

class MoviRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
): IMoviesRepository {

    override fun getMovie(): LiveData<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, MoviesResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Movies>> {
                return Transformations.map(localDataSource.getAllMovie()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean = data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<MoviesResponse>> =
                remoteDataSource.getMovie()

            override fun saveCallResult(data: MoviesResponse) {
                val moviesList = DataMapper.mapResponseToEntities(data.results)
                localDataSource.insertMovie(moviesList)
            }
        }.asLiveData()

    override fun getFavMovie(): LiveData<List<Movies>> {
        return Transformations.map(localDataSource.getFavMovie()) {
            DataMapper.mapEntitiesToDomain(it)
        }
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