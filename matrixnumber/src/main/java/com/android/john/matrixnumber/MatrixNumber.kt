package com.android.john.matrixnumber

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
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

        postDelayed({
            addMatrixText()
        },3000L)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        post(this::addNumberItems)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        addNumberItems()
        Log.e("John","onMeasure:$measuredWidth")

    }


    private fun addNumberItems(){
        val count = ( measuredWidth/textSize ).toInt()
        for (i in 0 until count){
            val item = MatrixNumberItem(context).apply {
                textSize = this@MatrixNumber.textSize
                normalColor = this@MatrixNumber.normalColor
                highLightColor = this@MatrixNumber.highLightColor
                startDelay = (Math.random() * 500).toLong()
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