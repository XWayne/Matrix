package com.android.john.matrixnumber

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout

class MatrixNumber :FrameLayout {

    constructor(context: Context):this(context,null)
    constructor(context: Context,attr:AttributeSet?):super(context,attr,0)

    private var textSize = DisplayUtil.sp2px(context,15f)
    private var normalColor = Color.GREEN
    private var highLightColor = Color.WHITE

    init {
//        orientation = HORIZONTAL
        setWillNotDraw(false)
        setBackgroundColor(Color.BLACK)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        post(this::addNumberItems)
        postDelayed(this::addMatrixText,3000L)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        for (i in 0 until childCount){
            getChildAt(i).postInvalidate()
        }

        postInvalidateDelayed(200L)
    }

    private fun addNumberItems(){
        val count = ( measuredWidth/textSize ).toInt()
        for (i in 0 until count){
            val item = MatrixNumberItem(context).apply {
                textSize = this@MatrixNumber.textSize
                normalColor = this@MatrixNumber.normalColor
                highLightColor = this@MatrixNumber.highLightColor
                startOffset = (Math.random() *8).toInt()
            }

            val layoutParameter = LayoutParams(textSize.toInt(),measuredHeight)
            layoutParameter.marginStart = textSize.toInt()*i
            addView(item,layoutParameter)
        }
    }

    private fun addMatrixText(){
        val matrixText = MatrixText(context)
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
        layoutParams.gravity = Gravity.NO_GRAVITY
        addView(matrixText,layoutParams)
    }

}