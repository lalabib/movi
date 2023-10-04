package com.latihan.lalabib.movi.data

import androidx.lifecycle.LiveData
import com.latihan.lalabib.movi.data.local.LocalDataSource
import com.latihan.lalabib.movi.data.local.entity.MoviesEntity
import com.latihan.lalabib.movi.data.remote.ApiResponse
import com.latihan.lalabib.movi.data.remote.RemoteDataSource
import com.latihan.lalabib.movi.data.remote.response.DetailMovieResponse
import com.latihan.lalabib.movi.data.remote.response.MoviesResponse
import com.latihan.lalabib.movi.utils.AppExecutors
import com.latihan.lalabib.movi.utils.Resource

class MoviRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : MoviDataSource {

    override fun getMovie(): LiveData<Resource<List<MoviesEntity>>> {
        return object : NetworkBoundResource<List<MoviesEntity>, MoviesResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MoviesEntity>> = localDataSource.getAllMovie()

            override fun shouldFetch(data: List<MoviesEntity>?): Boolean = data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<MoviesResponse>> =
                remoteDataSource.getMovie()

            override fun saveCallResult(data: MoviesResponse) =
                localDataSource.insertMovie(data.results)
        }.asLiveData()
    }

    override fun getDetailMovie(id: String): LiveData<Resource<MoviesEntity>> {
        return object : NetworkBoundResource<MoviesEntity, DetailMovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MoviesEntity> = localDataSource.getDetailMovie(id)

            override fun shouldFetch(data: MoviesEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> =
                remoteDataSource.getDetailMovie(id)

            override fun saveCallResult(data: DetailMovieResponse) {
                val movie = MoviesEntity(
                    data.id,
                    data.title,
                    data.overview,
                    data.releaseDate,
                    data.voteAverage,
                    data.posterPath
                )
                localDataSource.updateMovie(movie)
            }
        }.asLiveData()
    }

    override fun setFavoriteMovie(movie: MoviesEntity, isFavorite: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setMovieStatus(movie, isFavorite)
        }
    }

    override fun getFavMovie(): LiveData<List<MoviesEntity>> = localDataSource.getFavMovie()

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