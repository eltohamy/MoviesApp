package com.movies.data

import com.movies.data.remote.RemoteApi
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Tohamy on 29/09/2019
 */
@Singleton
class DataManager @Inject
constructor(val remoteApi: RemoteApi)
