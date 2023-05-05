package com.rupesh.myquote.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemDecorator(private var left: Int = 0, private var top: Int = 0, private var right: Int = 0, private var bottom: Int = 2) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(left, top, right, bottom)
    }

}