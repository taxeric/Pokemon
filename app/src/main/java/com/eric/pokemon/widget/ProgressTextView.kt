package com.eric.pokemon.widget

import android.animation.ValueAnimator
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
import com.eric.pokemon.utils.LogUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by eric on 20-12-17
 */
class ProgressTextView(context: Context, attrs: AttributeSet) : View(context, attrs),
    ValueAnimator.AnimatorUpdateListener {

    private val LINE_WIDTH = DpPxUtils.toPx(20f)
    private val DEFAULT_HEIGHT = DpPxUtils.toPx(20f)
    private val PADDING = DpPxUtils.toPx(20f)
    private val TEXT_SIZE = DpPxUtils.toPx(14f)

    private var animation: ValueAnimator ?= null

    //基础值
    var baseValue = 0
        set(value) {
            field = value
            updateAnimDuration = true
        }
    //最大值
    var maxValue = 0
    //显示内容
    var text = ""
    //自动播放
    private var autoPlay = true
    private var updateAnimDuration = false
    //动画时长
    private var animationDuration = 0

    private var allWidth = 0f
    private var shouldWidth = 0f
    private var currentValue = 0
    private val textBounds = Rect()

    private val backgroundLinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = LINE_WIDTH
        strokeCap = Paint.Cap.ROUND
    }
    private val foreLinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = LINE_WIDTH
        style = Paint.Style.FILL
        strokeCap = Paint.Cap.ROUND
    }
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = TEXT_SIZE
    }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressTextView)
        baseValue = typedArray.getInt(R.styleable.ProgressTextView_base_value, 0)
        maxValue = typedArray.getInt(R.styleable.ProgressTextView_max_value, 100)
        foreLinePaint.color = typedArray.getColor(R.styleable.ProgressTextView_fill_color, Color.BLACK)
        autoPlay = typedArray.getBoolean(R.styleable.ProgressTextView_auto_play, true)
        val str = typedArray.getString(R.styleable.ProgressTextView_text)
        if (!TextUtils.isEmpty(str)){
            text = str!!
        }
        textPaint.color = typedArray.getColor(R.styleable.ProgressTextView_text_color, Color.WHITE)
        backgroundLinePaint.color = typedArray.getColor(R.styleable.ProgressTextView_background_line_color, Color.GRAY)
        animationDuration = typedArray.getInt(R.styleable.ProgressTextView_animation_duration, 2)
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val heightResolve = resolveSize((DEFAULT_HEIGHT + PADDING).toInt(), heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), heightResolve)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        updateValue()
        if (autoPlay) {
            startAnimation()
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawLine(0f + PADDING, height / 2f, width.toFloat() - PADDING, height / 2f, backgroundLinePaint)
        canvas.drawLine(0f + PADDING, height / 2f, PADDING + currentValue, height / 2f, foreLinePaint)
        textPaint.getTextBounds(text, 0, text.length, textBounds)
        canvas.drawText(text,
            width - PADDING - textBounds.right - textBounds.left,
            height / 2f - (textBounds.top + textBounds.bottom) / 2f, textPaint)
    }

    private fun updateValue(){
        allWidth = width - PADDING * 2
        shouldWidth = baseValue.toFloat() / maxValue.toFloat() * allWidth
    }

    fun setAnimDuration(s: Int){
        animationDuration = s
    }

    fun isAutoPlay(): Boolean = autoPlay

    fun delayStartAnimation(s: Long){
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO){
                Thread.sleep(s)
            }
            withContext(Dispatchers.Main){
                startAnimation()
            }
        }
    }

    private fun setAnimationValue(){
        if (animation == null || updateAnimDuration){
            if (updateAnimDuration){
                updateValue()
                updateAnimDuration = false
            }
            animation = ValueAnimator.ofInt(0, shouldWidth.toInt()).apply {
                duration = (animationDuration * 1000).toLong()
                addUpdateListener(this@ProgressTextView)
            }
        }
    }

    fun startAnimation(){
        setAnimationValue()
        animation?.start()
    }

    fun stopAnimation(){
        animation?.pause()
    }

    override fun onAnimationUpdate(animationValueAnimator: ValueAnimator) {
        currentValue = animationValueAnimator.animatedValue as Int
        invalidate()
    }
}