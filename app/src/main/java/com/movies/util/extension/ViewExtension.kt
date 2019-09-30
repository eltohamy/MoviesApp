package com.movies.util.extension

import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Tohamy on 29/09/2019
 */
fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}