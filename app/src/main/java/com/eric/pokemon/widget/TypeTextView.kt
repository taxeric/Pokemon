package com.eric.pokemon.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.eric.pokemon.utils.Dp2Px
import com.eric.pokemon.utils.PokemonUtils

/**
 * Created by eric on 20-12-16
 */
class TypeTextView(context: Context, colorName: String): AppCompatTextView(context){

    private val paint = Paint().apply {
        color = PokemonUtils.switchPokemonTypeColor(colorName)
        style = Paint.Style.FILL
    }
    private val rectF = RectF(0f, 0f, 100f, 0f)
    private val paddingValue: Float = Dp2Px.toPx(10f)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(120, 70)
    }

    override fun onDraw(canvas: Canvas) {
        rectF.right = width.toFloat()
        rectF.bottom = height.toFloat()
        canvas.drawRect(rectF, paint)
        super.onDraw(canvas)
        setTextColor(Color.WHITE)
    }
}

