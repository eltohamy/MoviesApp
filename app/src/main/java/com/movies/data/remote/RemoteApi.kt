package com.movies.data.remote

import com.movies.data.models.movieslist.MoviesListResponse
import com.movies.data.models.searchkeywords.SearchKeywordsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Tohamy on 29/09/2019
 */
interface RemoteApi {

    @GET("movie/now_playing")
    fun getLatestMovies(@Query("page") page: Int, @Query("language") language: String, @Query("api_key") apiKey: String): Observable<MoviesListResponse>

    @GET("search/keyword")
    fun getSearchKeywords(@Query("page") page: Int, @Query("query") query: String, @Query("api_key") apiKey: String): Observable<SearchKeywordsResponse>

    @GET("search/movie")
    fun searchForMovies(@Query("page") page: Int, @Query("query") query: String, @Query("api_key") apiKey: String): Observable<MoviesListResponse>
}