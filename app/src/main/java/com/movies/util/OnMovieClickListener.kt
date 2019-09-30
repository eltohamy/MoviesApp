package com.movies.util

import android.view.View
import com.movies.ui.movieslist.adapters.MoviesViewModel

/**
 * Created by Tohamy on 29/09/2019
 */
interface OnMovieClickListener {
    fun onMovieClick(view: View, viewModel: MoviesViewModel)
}