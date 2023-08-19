package com.latihan.lalabib.movi.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.latihan.lalabib.movi.data.local.entity.MoviesEntity
import com.latihan.lalabib.movi.data.local.room.MoviDatabase
import com.latihan.lalabib.movi.data.remote.MoviRemoteMediator
import com.latihan.lalabib.movi.data.remote.RemoteDataSource
import com.latihan.lalabib.movi.data.remote.response.DetailMovieResponse
import com.latihan.lalabib.movi.networking.ApiService

class MoviRepository(
    private val remoteDataSource: RemoteDataSource,
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

    override fun getDetailMovie(id: String): LiveData<DetailMovieResponse> {
        val detailMovies = MutableLiveData<DetailMovieResponse>()
        remoteDataSource.getDetailMovie(id, object : RemoteDataSource.LoadDetailMovieCallback {
            override fun detailMovieReceived(detailMoviesResponse: DetailMovieResponse) {
                detailMovies.postValue(detailMoviesResponse)
            }
        })

        return detailMovies
    }

    companion object {
        @Volatile
        private var instance: MoviRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            database: MoviDatabase,
            apiService: ApiService
        ): MoviRepository = instance ?: synchronized(this) {
            instance ?: MoviRepository(remoteDataSource, database, apiService)
        }
    }
}