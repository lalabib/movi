package com.latihan.lalabib.movi.core.di

import com.latihan.lalabib.movi.core.BuildConfig
import com.latihan.lalabib.movi.core.data.source.remote.network.ApiService
import com.latihan.lalabib.movi.core.utils.SharedObject.BASE_URL
import com.latihan.lalabib.movi.core.utils.SharedObject.certificatePinner1
import com.latihan.lalabib.movi.core.utils.SharedObject.certificatePinner2
import com.latihan.lalabib.movi.core.utils.SharedObject.certificatePinner3
import com.latihan.lalabib.movi.core.utils.SharedObject.hostname
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, certificatePinner1)
            .add(hostname, certificatePinner2)
            .add(hostname, certificatePinner3)
            .build()

        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                )
            )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    @Provides
    fun provideApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}