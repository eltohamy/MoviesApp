package com.movies.data.models.movieslist

/**
 * Created by Tohamy on 29/09/2019
 */
data class MoviesListResponse(

    val results: List<Movie>,
    val page: Int,
    val total_results: Int,
    val dates: Dates,
    val total_pages: Int
)