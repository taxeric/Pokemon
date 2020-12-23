package com.eric.pokemon.base

import android.view.View

/**
 * Created by eric on 20-12-11
 */
interface OnClickListener {

    fun onItemClick(view: View, order: Int){}

    fun onItemClick(loadMore: Boolean, position: Int){}
}

