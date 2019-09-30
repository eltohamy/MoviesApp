package com.movies.di.component

import com.movies.data.DataManager
import com.movies.di.module.ApplicationModule
import com.movies.di.module.NetworkModule
import com.movies.ui.base.BaseActivity
import com.movies.ui.base.BaseFragment
import com.movies.ui.movieslist.MoviesListViewModel
import com.movies.ui.movieslist.adapters.MoviesViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Tohamy on 29/09/2019
 */
@Singleton
@Component(modules = [NetworkModule::class, ApplicationModule::class])
interface ApplicationComponent {

    fun dataManager(): DataManager
    fun inject(baseFragment: BaseFragment)
    fun inject(baseActivity: BaseActivity)

    /**
     * Injects required dependencies into the specified MoviesListViewModel.
     * @param moviesListViewModel MoviesListViewModel in which to inject the dependencies
     */
    fun inject(moviesListViewModel: MoviesListViewModel)

    /**
     * Injects required dependencies into the specified MovieViewModel.
     * @param movieViewModel MovieViewModel in which to inject the dependencies
     */
    fun inject(movieViewModel: MoviesViewModel)

}

