package com.movies.ui.movieslist

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movies.Const
import com.movies.R
import com.movies.data.DataManager
import com.movies.data.models.movieslist.Movie
import com.movies.ui.movieslist.adapters.MoviesListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Tohamy on 29/09/2019
 */
class MoviesSearchViewModel @Inject
internal constructor(private val dataManager: DataManager) : ViewModel() {

    val moviesListAdapter: MoviesListAdapter = MoviesListAdapter()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val loadMoreVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener {
        page = 1
        searchForMovies(page, query)
    }
    private var page = 1
    private var totalPages: Int = 0
    private var query: String = ""

    // search for movies
    fun searchForMovies(page: Int, query: String) {
        setQuery(query)
        compositeDisposable.add(dataManager.remoteApi.searchForMovies(page, query, Const.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onSubscribe(page) }
            .doOnTerminate { onTerminate(page) }
            .subscribe(
                { moviesResponse ->
                    if (moviesResponse != null) {
                        totalPages = moviesResponse.total_pages
                        if (moviesResponse.results.isNotEmpty())
                            displayMovies(page, moviesResponse.results)
                        else if (page == 1)
                            onEmpty()
                    } else
                        onError()
                },
                { onError() }
            ))
    }

    private fun onSubscribe(page: Int) {
        if (page == 1) {
            loadingVisibility.value = View.VISIBLE
            errorMessage.value = null
        }
    }

    private fun onTerminate(page: Int) {
        loadingVisibility.value = View.GONE
        if (page == totalPages)
            loadMoreVisibility.value = View.GONE
    }

    //set or add movies list to Adapter
    private fun displayMovies(page: Int, movies: List<Movie>) {
        if (page > 1)
            moviesListAdapter.addMoviesList(movies)
        else
            moviesListAdapter.updateMoviesList(movies)
    }

    //display error message
    private fun onError() {
        errorMessage.value = R.string.error
    }

    //display empty message
    private fun onEmpty() {
        errorMessage.value = R.string.empty_response
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    // load more movie to load all pages
    fun loadMoreMovies() {
        page++
        if (page <= totalPages)
            searchForMovies(page, query)
        else
            onTerminate(totalPages)
    }

    private fun setQuery(query: String) {
        this.query = query
    }
}