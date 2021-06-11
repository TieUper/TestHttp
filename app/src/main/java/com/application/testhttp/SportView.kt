package com.application.testhttp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
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

    paint.style = Paint.Style.FILL
    paint.getTextBounds("abab", 0, "abab".length, bounds)
    canvas.drawText("abab", width / 2f, height / 2f - (bounds.top + bounds.bottom) / 2f, paint)
  }
}