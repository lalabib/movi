package com.latihan.lalabib.movi.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.latihan.lalabib.movi.data.remote.RemoteDataSource
import com.latihan.lalabib.movi.data.remote.response.DetailMovieResponse
import com.latihan.lalabib.movi.data.remote.response.MoviesResponse

class MoviRepository(private val remoteDataSource: RemoteDataSource) : MoviDataSource {

    override fun getMovie(apiKey: String): LiveData<MoviesResponse> {
        val movies = MutableLiveData<MoviesResponse>()
        remoteDataSource.getMovie(apiKey, object : RemoteDataSource.LoadMovieCallback {
            override fun allMovieReceived(moviesResponse: MoviesResponse) {
                movies.postValue(moviesResponse)
            }
        })

        return movies
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

        fun getInstance(remoteDataSource: RemoteDataSource)
                : MoviRepository = instance ?: synchronized(this) {
            instance ?: MoviRepository(remoteDataSource)
        }
    }
}