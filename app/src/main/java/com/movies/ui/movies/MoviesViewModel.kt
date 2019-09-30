package com.movies.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movies.Const
import com.movies.data.DataManager
import com.movies.data.models.searchkeywords.SearchKeyword
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Tohamy on 29/09/2019
 */
class MoviesViewModel @Inject
internal constructor(private val dataManager: DataManager) : ViewModel() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var page = 1
    private var totalPages: Int = 0

    val searchKeywords: MutableLiveData<List<String>> = MutableLiveData()

    // load search key words
    fun loadSearchKeywords(page: Int, query: String) {
        compositeDisposable.add(dataManager.remoteApi.getSearchKeywords(
            page, query, Const.API_KEY
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { searchKeywordsResponse ->
                    if (searchKeywordsResponse != null) {
                        totalPages = searchKeywordsResponse.total_pages
                        if (searchKeywordsResponse.results.isNotEmpty())
                            setSearchKeywords(page, searchKeywordsResponse.results)
                        loadMoreSearchKeywords(query)
                    } else
                        onError()
                },
                { onError() }
            ))
    }

    private fun onError() {

    }

    //update search key words
    private fun setSearchKeywords(page: Int, searchKeywordsList: List<SearchKeyword>) {
        if (page > 1)
            searchKeywords.value = searchKeywordsList.map { it.name }
        else
            searchKeywords.postValue(searchKeywordsList.map { it.name })
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    //load more search keywords if still exist
    private fun loadMoreSearchKeywords(query: String) {
        page++
        if (page <= totalPages)
            loadSearchKeywords(page, query)
    }
}