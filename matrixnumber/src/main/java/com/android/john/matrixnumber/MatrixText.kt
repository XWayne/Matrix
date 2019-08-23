package com.android.john.matrixnumber

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MatrixText :View{
    constructor(context: Context):this(context,null)
    constructor(context: Context, attr: AttributeSet?):super(context,attr,0)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val text = "M A T R I X"
    private val textSize =  DisplayUtil.sp2px(context,50f)

    init {
        paint.textSize =textSize
        paint.color = Color.parseColor("#ccffffff")
//        paint.isFakeBoldText = true

        setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val textWidth = paint.measureText(text)
        val px = width/2 - textWidth/2
        val py = height/2 + textSize/2

        canvas?.drawText(text,px,py,paint)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        alpha = 0f
        animate().alpha(1f).setDuration(5000L).start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animate().cancel()
    }
}