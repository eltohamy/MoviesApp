package com.movies.di.component

import com.movies.di.PerActivity
import com.movies.di.module.ActivityModule
import com.movies.ui.base.BaseFragment
import com.movies.ui.movieslist.MoviesListFragment
import dagger.Subcomponent

/**
 * Created by Tohamy on 29/09/2019
 */
@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(baseFragment: BaseFragment)
    fun inject(moviesListFragment: MoviesListFragment)

}