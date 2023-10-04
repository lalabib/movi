package com.latihan.lalabib.movi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.latihan.lalabib.movi.data.MoviRepository
import com.latihan.lalabib.movi.data.local.entity.MoviesEntity
import com.latihan.lalabib.movi.utils.Resource

class HomeViewModel(repository: MoviRepository): ViewModel() {

    val movie: LiveData<Resource<List<MoviesEntity>>> = repository.getMovie()
}