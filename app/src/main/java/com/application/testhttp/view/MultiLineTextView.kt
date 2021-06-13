package com.application.testhttp.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.application.testhttp.R
import com.hencoder.text.dp

class MultiLineTextView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    val content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur tristique urna tincidunt maximus viverra. Maecenas commodo pellentesque dolor ultrices porttitor. Vestibulum in arcu rhoncus, maximus ligula vel, consequat sem. Maecenas a quam libero. Praesent hendrerit ex lacus, ac feugiat nibh interdum et. Vestibulum in gravida neque. Morbi maximus scelerisque odio, vel pellentesque purus ultrices quis. Praesent eu turpis et metus venenatis maximus blandit sed magna. Sed imperdiet est semper urna laoreet congue. Praesent mattis magna sed est accumsan posuere. Morbi lobortis fermentum fringilla. Fusce sed ex tempus, venenatis odio ac, tempor metus."

    val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG) .apply {
        textSize = 16.dp
    }

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }

    val fontMetrics = paint.fontMetrics

    val fontSpacing = paint.fontSpacing

    private val IMAGE_SIZE = 150.dp
    private val IMAGE_PADDING = 50.dp
    private val bitmap = getAvatar(IMAGE_SIZE.toInt())

    override fun onDraw(canvas: Canvas) {
        //正常情况下 drawText只会绘制一行
//        canvas.drawText(content, 0f, - fontMetrics.top, paint)

        //使用StaticLayout 可以显示多行文本
//        val staticLayout = StaticLayout(content, textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false)
//        staticLayout.draw(canvas)

        canvas.drawBitmap(bitmap, width - IMAGE_SIZE, IMAGE_PADDING,paint)

        var floatArrayOf = floatArrayOf(0f)

        println("fontSpacing: ${paint.fontSpacing}   leading: ${fontMetrics.leading}")

        var start = 0
        var end: Int
        var vertical = -fontMetrics.top

        var maxWidth: Float

        while (start < content.length) {
            maxWidth = if (vertical + fontMetrics.bottom < IMAGE_PADDING || vertical + fontMetrics.top > IMAGE_PADDING + IMAGE_SIZE) {
                width.toFloat()
            }else {
                width - IMAGE_SIZE
            }
            end = paint.breakText(content, start, content.length, true, maxWidth, floatArrayOf)
            canvas.drawText(content, start, start + end, 0f, vertical, paint)
            start += end
            vertical += fontSpacing
        }
    }

    private fun getAvatar(width: Int) : Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
    }
}