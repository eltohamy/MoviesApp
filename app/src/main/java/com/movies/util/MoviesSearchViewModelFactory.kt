package com.movies.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movies.data.DataManager
import com.movies.ui.movieslist.MoviesSearchViewModel

/**
 * Created by Tohamy on 29/09/2019
 */
class MoviesSearchViewModelFactory(private val dataManager: DataManager) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesSearchViewModel(dataManager) as T
    }
}