package com.application.testhttp.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

/**
 * 仪表盘绘制
 */
class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    val angle = 120f

    val pathMeasure = PathMeasure()

    val DashWidth = dp2px(2f)
    val DashLength = dp2px(10f)

    val dash = Path()

    val path = Path()

    var length = 0f

    val position = 6

    private lateinit var pathEffect : PathDashPathEffect


    init {
        paint.strokeWidth = dp2px(3f)
        paint.style = Paint.Style.STROKE

        dash.addRect(0f,0f, DashWidth, DashLength, Path.Direction.CCW)

    }

    override fun onDraw(canvas: Canvas) {
//        canvas.drawArc(dp2px(50f),  dp2px(50f), dp2px(350f), dp2px(350f), 90f + angle.div(2), 360 - angle,  false, paint)
        canvas.drawPath(path, paint)

        paint.pathEffect = pathEffect
        canvas.drawPath(path, paint)

        paint.pathEffect = null

        canvas.drawLine(dp2px(200f),dp2px(200f),dp2px(200f) + dp2px(120f) * cos(markToRadians(position).toFloat()),dp2px(200f) + dp2px(120f) * sin(markToRadians(position).toFloat()), paint)
    }

    private fun markToRadians(mark: Int) = Math.toRadians((90f + angle.div(2) + (360- angle) / 20f * mark).toDouble())

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        path.addArc(dp2px(50f),  dp2px(50f), dp2px(350f), dp2px(350f), 90f + angle.div(2), 360 - angle)
        pathMeasure.setPath(path, false)
        pathEffect =  PathDashPathEffect(dash, (pathMeasure.length - DashWidth) / 20f, 0f, PathDashPathEffect.Style.ROTATE)
    }
}