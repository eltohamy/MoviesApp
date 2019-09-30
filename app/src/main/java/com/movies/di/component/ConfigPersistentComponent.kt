package com.movies.di.component


import com.movies.di.ConfigPersistent
import com.movies.di.module.ActivityModule
import dagger.Component

/**
 * Created by Tohamy on 29/09/2019
 */
@ConfigPersistent
@Component(dependencies = [ApplicationComponent::class])
interface ConfigPersistentComponent {
    fun activityComponent(activityModule: ActivityModule): ActivityComponent
}
