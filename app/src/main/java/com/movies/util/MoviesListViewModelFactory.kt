package com.movies.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movies.data.DataManager
import com.movies.ui.movieslist.MoviesListViewModel

/**
 * Created by Tohamy on 29/09/2019
 */
class MoviesListViewModelFactory(private val dataManager: DataManager) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesListViewModel(dataManager) as T
    }
}