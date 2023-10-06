package com.latihan.lalabib.movi.data.source.remote

import android.util.Log
import com.latihan.lalabib.movi.data.source.remote.response.DetailMovieResponse
import com.latihan.lalabib.movi.data.source.remote.network.ApiResponse
import com.latihan.lalabib.movi.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getMovie(): Flow<ApiResponse<List<DetailMovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovie()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource(service)
        }

        private const val TAG = "RemoteData"
    }
}