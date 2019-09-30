package com.movies

import android.app.Application
import android.content.Context
import com.movies.di.component.ApplicationComponent
import com.movies.di.component.DaggerApplicationComponent
import com.movies.di.module.NetworkModule

/**
 * Created by Tohamy on 29/09/2019
 */
class AppControl : Application() {

    private var mApplicationComponent: ApplicationComponent? = null

    val component: ApplicationComponent?
        get() {
            if (mApplicationComponent == null) {
                mApplicationComponent = DaggerApplicationComponent.builder()
                    .networkModule(NetworkModule())
                    .build()
            }
            return mApplicationComponent
        }

    companion object {
        operator fun get(context: Context): AppControl {
            return context.applicationContext as AppControl
        }
    }

}