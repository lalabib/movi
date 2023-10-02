package com.latihan.lalabib.movi.data

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.latihan.lalabib.movi.data.local.LocalDataSource
import com.latihan.lalabib.movi.data.local.entity.MoviesEntity
import com.latihan.lalabib.movi.data.local.room.MoviDatabase
import com.latihan.lalabib.movi.data.remote.ApiResponse
import com.latihan.lalabib.movi.data.remote.MoviRemoteMediator
import com.latihan.lalabib.movi.data.remote.RemoteDataSource
import com.latihan.lalabib.movi.data.remote.response.DetailMovieResponse
import com.latihan.lalabib.movi.networking.ApiService
import com.latihan.lalabib.movi.utils.AppExecutors
import com.latihan.lalabib.movi.utils.Resource

class MoviRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
    private val database: MoviDatabase,
    private val apiService: ApiService
) : MoviDataSource {

    fun getAllMovie(): LiveData<PagingData<MoviesEntity>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = 8),
            remoteMediator = MoviRemoteMediator(database, apiService),
            pagingSourceFactory = {
                database.movieDao().getAllMovie()
            }
        ).liveData
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

    companion object {
        @Volatile
        private var instance: MoviRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors,
            database: MoviDatabase,
            apiService: ApiService
        ): MoviRepository = instance ?: synchronized(this) {
            instance ?: MoviRepository(
                remoteDataSource,
                localDataSource,
                appExecutors,
                database,
                apiService
            )
        }
    }
}