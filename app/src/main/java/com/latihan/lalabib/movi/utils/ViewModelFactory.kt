package com.latihan.lalabib.movi.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.latihan.lalabib.movi.data.MoviRepository
import com.latihan.lalabib.movi.di.Injection
import com.latihan.lalabib.movi.ui.detail.DetailViewModel
import com.latihan.lalabib.movi.ui.home.HomeViewModel

class ViewModelFactory(private val moviRepository: MoviRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(moviRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(moviRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel Class: $modelClass")
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository())
            }
    }
}