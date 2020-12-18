package com.eric.pokemon.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import com.eric.pokemon.R
import com.eric.pokemon.utils.DpPxUtils

/**
 * Created by eric on 20-12-18
 */
class TagTextView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val PADDING = DpPxUtils.toPx(10f)
    private val TEXT_SIZE = DpPxUtils.toPx(15f)
    private val DEFAULT_HEIGHT = DpPxUtils.toPx(30f)
    private val LINE_WIDTH = DpPxUtils.toPx(1f)

    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = TEXT_SIZE
    }
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        strokeWidth = LINE_WIDTH
    }
    private val textRect = Rect()

    var text = ""

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TagTextView)
        textPaint.color = typedArray.getColor(R.styleable.TagTextView_text_color, Color.BLACK)
        val str = typedArray.getString(R.styleable.TagTextView_text)
        if (!TextUtils.isEmpty(str)){
            text = str!!
        }
        linePaint.color = typedArray.getColor(R.styleable.TagTextView_line_color, Color.GRAY)
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val resolveHeight = resolveSize(DEFAULT_HEIGHT.toInt(), heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), resolveHeight)
    }

    override fun onDraw(canvas: Canvas) {
        textPaint.getTextBounds(text, 0, text.length, textRect)
        canvas.drawText(text, 0f + PADDING, height / 2f - textRect.bottom + PADDING / 2f, textPaint)
        canvas.drawLine(0f + PADDING, height / 2f + PADDING, width - PADDING, height / 2f + PADDING, linePaint)
    }
}