package com.latihan.lalabib.movi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.latihan.lalabib.movi.data.MoviRepository
import com.latihan.lalabib.movi.data.local.entity.MoviesEntity
class HomeViewModel(repository: MoviRepository): ViewModel() {

    val movie: LiveData<PagingData<MoviesEntity>> = repository.getAllMovie().cachedIn(viewModelScope)
}