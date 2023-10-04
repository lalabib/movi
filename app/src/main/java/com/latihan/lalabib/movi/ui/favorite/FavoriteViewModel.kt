package com.latihan.lalabib.movi.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.latihan.lalabib.movi.data.MoviRepository
import com.latihan.lalabib.movi.data.local.entity.MoviesEntity

class FavoriteViewModel(repository: MoviRepository): ViewModel() {

    val favoriteMovie: LiveData<List<MoviesEntity>> = repository.getFavMovie()
}