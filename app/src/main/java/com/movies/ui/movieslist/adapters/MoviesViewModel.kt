package com.movies.ui.movieslist.adapters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movies.data.models.movieslist.Movie

/**
 * Created by Tohamy on 29/09/2019
 */
class MoviesViewModel : ViewModel() {
    private val title = MutableLiveData<String>()
    private val backDropPath = MutableLiveData<String>()
    private lateinit var movie: Movie

    fun bind(movies: Movie) {
        this.movie = movies
        this.title.value = movie.title
        this.backDropPath.value = movie.backdrop_path
    }

    fun getMovieTitle(): MutableLiveData<String> {
        return title
    }

    fun getBackDropPath(): MutableLiveData<String> {
        return backDropPath
    }

    fun getMovie(): Movie {
        return movie
    }
}