package com.eric.pokemon.utils

import android.content.res.Resources
import android.util.TypedValue

/**
 * Created by eric on 20-12-16
 */
object DpPxUtils {

    private val DISPLAY_METRICS = Resources.getSystem().displayMetrics

    fun toPx(value: Float): Float{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, DISPLAY_METRICS)
    }
}