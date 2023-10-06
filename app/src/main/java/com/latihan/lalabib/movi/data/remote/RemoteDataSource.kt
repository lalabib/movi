package com.latihan.lalabib.movi.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.latihan.lalabib.movi.networking.ApiConfig
import com.latihan.lalabib.movi.BuildConfig.apiKey
import com.latihan.lalabib.movi.data.remote.network.ApiResponse
import com.latihan.lalabib.movi.data.remote.response.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getMovie(): LiveData<ApiResponse<MoviesResponse>> {
        val resultMovie = MutableLiveData<ApiResponse<MoviesResponse>>()
        ApiConfig.getApiService().getMovie(apiKey).enqueue(object : Callback<MoviesResponse>{
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { resultMovie.value = ApiResponse.Success(it) }
                } else {
                    Log.e(TAG, "onResponseFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return resultMovie
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