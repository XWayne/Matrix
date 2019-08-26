package com.android.john.matrixnumber

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.abs
import kotlin.math.min

typealias UpdateListener = ()->Unit

internal class MatrixNumberItem :View{
    constructor(context: Context):this(context,null)
    constructor(context: Context, attr: AttributeSet?):super(context,attr,0)



    var normalColor = Color.GREEN
        set(value) {
            field = value
            if (isAttachedToWindow){
                postInvalidate()
            }
        }

    var highLightColor = Color.WHITE
        set(value) {
            field = value
            if (isAttachedToWindow){
                postInvalidate()
            }
        }

    var textSize :Float= DisplayUtil.sp2px(context,15f)
        set(value) {
            field = value
            if (isAttachedToWindow){
                postInvalidate()
            }
        }

    var startOffset = 0

    private val isNumberFullScreen:Boolean
        get() = currentHeight >= height

    private var currentHeight = 0f

    private var highLightIndex = 0

    private val paint:Paint

    private val fontMetrics = Paint.FontMetrics()

    private lateinit var updateListener:UpdateListener

    init {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)

    }



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (startOffset-->0)
            return

        canvas?.let {
            paint.textSize = textSize
            paint.getFontMetrics(fontMetrics)
            val textHeight = abs(fontMetrics.descent - fontMetrics.ascent)
            val textCount =
                if (isNumberFullScreen) ( height/textHeight ).toInt()
                else ( currentHeight/textHeight ).toInt()

            if (textCount>0){
                drawNumber(it,textCount)
            }

//            if (textCount == 0){
//                postInvalidateDelayed(startOffset)
//            }else{
//                drawNumber(it,textCount)
//            }

            currentHeight = min(currentHeight+textHeight,height.toFloat())

        }
    }

    private fun drawNumber(canvas: Canvas,textCount:Int){

        for (i in 0 until textCount){

            val textHeight = abs(fontMetrics.descent - fontMetrics.ascent)

            paint.color = if (i == highLightIndex)highLightColor else normalColor

            val text = ( Math.random()*9 ).toInt().toString()

            canvas.drawText(text,0f,(1+i)*textHeight,paint)
        }

        highLightIndex++
        if (isNumberFullScreen){
            highLightIndex %= textCount
        }

//        postInvalidateDelayed(100L)
    }



}