package com.latihan.lalabib.movi.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.latihan.lalabib.movi.data.remote.response.DetailMovieResponse
import com.latihan.lalabib.movi.networking.ApiConfig
import com.latihan.lalabib.movi.BuildConfig.apiKey
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getDetailMovie(id: String): LiveData<ApiResponse<DetailMovieResponse>> {
        val resultDetailMovie = MutableLiveData<ApiResponse<DetailMovieResponse>>()
        ApiConfig.getApiService().getDetailMovie(id, apiKey)
            .enqueue(object : Callback<DetailMovieResponse> {
                override fun onResponse(
                    call: Call<DetailMovieResponse>,
                    response: Response<DetailMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { resultDetailMovie.value = ApiResponse.success(it) }
                    } else {
                        Log.e(TAG, "onResponseFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }
            })
        return resultDetailMovie
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource()
        }

        private const val TAG = "RemoteData"
    }
}