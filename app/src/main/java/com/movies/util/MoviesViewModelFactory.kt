package com.movies.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movies.data.DataManager
import com.movies.ui.movies.MoviesViewModel

/**
 * Created by Tohamy on 29/09/2019
 */
class MoviesViewModelFactory(private val dataManager: DataManager) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesViewModel(dataManager) as T
    }
}