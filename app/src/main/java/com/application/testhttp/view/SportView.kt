package com.application.testhttp.view

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.withSave
import com.application.testhttp.R
import com.hencoder.text.dp


private val CIRCLE_COLOR = Color.parseColor("#90A4AE")
private val HIGHLIGHT_COLOR = Color.parseColor("#FF4081")
private val RING_WIDTH = 20.dp
private val RADIUS = 150.dp

class SportView(context: Context, attrs: AttributeSet?) :
  View(context, attrs) {
  private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    textSize = 100.dp
    typeface = ResourcesCompat.getFont(context, R.font.font)
    textAlign = Paint.Align.CENTER
  }
  private val bounds = Rect()
  private val fontMetrics = Paint.FontMetrics()

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    // 绘制环
    paint.style = Paint.Style.STROKE
    paint.color = CIRCLE_COLOR
    paint.strokeWidth = RING_WIDTH

    canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)

    // 绘制进度条
    paint.color = HIGHLIGHT_COLOR
    paint.strokeCap = Paint.Cap.ROUND
    canvas.drawArc(width / 2f - RADIUS, height / 2f - RADIUS, width / 2f + RADIUS, height / 2f + RADIUS, -90f, 225f, false, paint)

    //静态文字 纵向居中
//    paint.style = Paint.Style.FILL
//    paint.getTextBounds("abab", 0, "abab".length, bounds)
//    canvas.drawText("abab", width / 2f, height / 2f - (bounds.top + bounds.bottom) / 2f, paint)

    //动态文字， 纵向居中
    paint.style = Paint.Style.FILL
    paint.getFontMetrics(fontMetrics)
    var top = fontMetrics.top
    canvas.drawText("aaaa", width/2f, height/ 2f - (fontMetrics.ascent + fontMetrics.descent) / 2f, paint)

    //左对齐，上面对齐
    paint.textSize = 150.dp
    paint.textAlign = Paint.Align.LEFT
    paint.getTextBounds("abcg", 0, "abcg".length, bounds)
    println("font mestrics  top: ${fontMetrics.top}  bottom: ${fontMetrics.bottom}   ascent: ${fontMetrics.ascent}  descent: ${fontMetrics.descent} leading: ${fontMetrics.leading}")
    println("font bounds  top: ${bounds.top}  bottom: ${bounds.bottom}")
    canvas.drawText("abcg", -bounds.left.toFloat(), - bounds.top.toFloat(), paint)


    paint.textSize = 15.dp
    paint.getTextBounds("abab", 0, "abab".length, bounds)
    canvas.drawText("abab", - bounds.left.toFloat(), -bounds.top.toFloat(), paint)

  }
}