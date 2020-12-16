package com.eric.pokemon.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.view.View
import com.eric.pokemon.utils.LogUtils
import com.eric.pokemon.utils.PokemonUtils

/**
 * Created by eric on 20-12-16
 */
class PkmTypeTextView(context: Context, type: String) : View(context) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = PokemonUtils.switchPokemonTypeColor(type)
        style = Paint.Style.FILL
    }
    private val rectf = RectF(0f, 0f, 0f, 0f)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(100, 60)
    }

    override fun onDraw(canvas: Canvas) {
        rectf.right = width.toFloat()
        rectf.bottom = height.toFloat()
        LogUtils.i("right ${rectf.right}  bottom ${rectf.bottom}")
        canvas.drawRect(rectf, paint)
    }
}