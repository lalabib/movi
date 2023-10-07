package com.latihan.lalabib.movi.core.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.latihan.lalabib.movi.core.domain.usecase.MoviesUseCase
import com.latihan.lalabib.movi.detail.DetailViewModel
import com.latihan.lalabib.movi.di.AppScope
import com.latihan.lalabib.movi.favorite.FavoriteViewModel
import com.latihan.lalabib.movi.home.HomeViewModel
import javax.inject.Inject

@AppScope
class ViewModelFactory @Inject constructor(private val moviesUseCase: MoviesUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(moviesUseCase) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(moviesUseCase) as T
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(moviesUseCase) as T
            else -> throw Throwable("Unknown ViewModel Class: $modelClass")
        }
    }
}