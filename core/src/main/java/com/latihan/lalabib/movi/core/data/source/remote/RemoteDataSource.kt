package com.latihan.lalabib.movi.core.data.source.remote

import android.util.Log
import com.latihan.lalabib.movi.core.data.source.remote.response.DetailMovieResponse
import com.latihan.lalabib.movi.core.data.source.remote.network.ApiResponse
import com.latihan.lalabib.movi.core.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

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
        private const val TAG = "RemoteData"
    }
}