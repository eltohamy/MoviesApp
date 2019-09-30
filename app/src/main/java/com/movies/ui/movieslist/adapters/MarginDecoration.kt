package com.movies.ui.movieslist.adapters

import android.content.Context
import android.graphics.Rect
import android.view.View

import androidx.recyclerview.widget.RecyclerView

import com.movies.R

/**
 * Created by Tohamy on 2/19/2018.
 */
class MarginDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val margin: Int = context.resources.getDimensionPixelSize(R.dimen.item_margin)

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.set(margin, margin, margin, margin)
    }
}
