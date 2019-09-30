package com.movies.di.module


import android.app.Application
import android.content.Context
import com.movies.di.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Created by Tohamy on 29/09/2019
 */
@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }
}
