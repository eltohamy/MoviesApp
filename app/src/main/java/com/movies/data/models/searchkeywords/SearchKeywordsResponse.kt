package com.movies.data.models.searchkeywords

/**
 * Created by Tohamy on 29/09/2019
 */
data class SearchKeywordsResponse(
    val page: Int,
    val results: List<SearchKeyword>,
    val total_pages: Int,
    val total_results: Int
)
