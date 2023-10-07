package com.latihan.lalabib.movi.core.di

import com.latihan.lalabib.movi.core.data.source.remote.network.ApiService
import com.latihan.lalabib.movi.core.utils.SharedObject
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(SharedObject.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}