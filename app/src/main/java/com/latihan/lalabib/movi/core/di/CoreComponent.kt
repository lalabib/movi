package com.latihan.lalabib.movi.core.di

import android.content.Context
import com.latihan.lalabib.movi.core.domain.repository.IMoviesRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideRepository(): IMoviesRepository
}