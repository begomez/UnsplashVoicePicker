package com.bgomez.picker.ui.adapters


import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject


/**
 * List decorator
 *
 * Created by bernatgomez on August,2020
 */
class GridDecoration @Inject constructor(
    private val pad : Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State) {

        super.getItemOffsets(outRect, view, parent, state)

        outRect!!.top = pad
        outRect!!.right = pad
        outRect!!.bottom = pad
        outRect!!.left = pad
    }
}