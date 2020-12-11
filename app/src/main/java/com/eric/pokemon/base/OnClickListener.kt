package com.eric.pokemon.base

import android.view.View

/**
 * Created by eric on 20-12-11
 */
interface OnClickListener {

    fun onItemClick(position: Int){}

    fun onItemClick(view: View, order: Int){}
}

