package com.latihan.lalabib.movi.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.latihan.lalabib.movi.di.Injection
import com.latihan.lalabib.movi.domain.usecase.MoviesUseCase
import com.latihan.lalabib.movi.ui.detail.DetailViewModel
import com.latihan.lalabib.movi.ui.favorite.FavoriteViewModel
import com.latihan.lalabib.movi.ui.home.HomeViewModel

class ViewModelFactory(private val moviesUseCase: MoviesUseCase) :
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

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideMoviesUseCase(context))
            }
    }
}