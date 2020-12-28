package com.eric.pokemon.widget

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.eric.pokemon.utils.DpPxUtils
import com.eric.pokemon.utils.PokemonUtils

/**
 * Created by eric on 20-12-16
 */
class PkmTypeTextView: View{

    private val DEFAULT_PAINT_TEXT_SIZE = DpPxUtils.toPx(18f)
    private val DEFAULT_WIDTH_SIZE = DpPxUtils.toPx(70f)
    private val DEFAULT_HEIGHT_SIZE = DpPxUtils.toPx(35f)
    private val DEFAULT_RADIUS_RECTF = DpPxUtils.toPx(5f)

    constructor(context: Context): super(context)

    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet)

    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        textSize = DEFAULT_PAINT_TEXT_SIZE
        style = Paint.Style.FILL
    }
    private val backgroundRectf = RectF(0f, 0f, 0f, 0f)
    private val textRectf = Rect()

    var text = "sbv"
        set(value) {
            field = value
            invalidate()
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthResolve = resolveSize(DEFAULT_WIDTH_SIZE.toInt(), widthMeasureSpec)
        val heightResolve = resolveSize(DEFAULT_HEIGHT_SIZE.toInt(), heightMeasureSpec)
        setMeasuredDimension(widthResolve, heightResolve)
    }

    override fun onDraw(canvas: Canvas) {
        backgroundRectf.right = width.toFloat()
        backgroundRectf.bottom = height.toFloat()
        backgroundPaint.color = PokemonUtils.switchPokemonTypeColor(text)
        canvas.drawRoundRect(backgroundRectf, DEFAULT_RADIUS_RECTF, DEFAULT_RADIUS_RECTF, backgroundPaint)
        textPaint.getTextBounds(text, 0, text.length, textRectf)
        canvas.drawText(text, width / 2f - (textRectf.left + textRectf.right) / 2f, height / 2f - (textRectf.top + textRectf.bottom) / 2f, textPaint)
    }
}