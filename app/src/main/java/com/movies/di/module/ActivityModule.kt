package com.movies.di.module


import android.app.Activity
import android.content.Context
import com.movies.di.ActivityContext
import dagger.Module
import dagger.Provides

/**
 * Created by Tohamy on 29/09/2019
 */
@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    internal fun provideActivity(): Activity {
        return mActivity
    }

    @Provides
    @ActivityContext
    internal fun providesContext(): Context {
        return mActivity
    }
}
