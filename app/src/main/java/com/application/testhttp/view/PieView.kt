package com.application.testhttp.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

/**
 * 饼图绘制
 */
class PieView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val angles = floatArrayOf(60f,90f, 150f, 60f)

    private val colors = listOf(Color.BLUE, Color.CYAN, Color.GREEN, Color.RED)

    private val OFFSET_LENGTH = dp2px(20f)

    private val position = 1


    init {
        paint.strokeWidth = dp2px(3f)
        paint.style = Paint.Style.FILL

    }

    override fun onDraw(canvas: Canvas) {
        var startAngle = 0f
        for ((index,value) in angles.withIndex()) {
            if (index == position) {
                canvas.save()
                canvas.translate(OFFSET_LENGTH * cos(Math.toRadians(startAngle + value / 2.toDouble())).toFloat(), OFFSET_LENGTH * sin(Math.toRadians(startAngle + value / 2.toDouble())).toFloat())
            }
            paint.color = colors[index]
            canvas.drawArc(dp2px(50f),  dp2px(50f), dp2px(350f), dp2px(350f), startAngle, value, true, paint)
            startAngle += value

            if (index == position) {
                canvas.restore()
            }
        }
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

    }


}