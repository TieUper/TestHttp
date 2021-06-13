package com.application.testhttp.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.appcompat.widget.AppCompatTextView



/**
 * xfermode
 */
class MyView(context: Context?, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    private val radius = dp2px(50f)

    val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)

    lateinit var pathMeasure: PathMeasure

    val descBitmap = Bitmap.createBitmap(dp2pxToInt(150f),dp2pxToInt(150f),Bitmap.Config.ARGB_8888)
    val srcBitmap = Bitmap.createBitmap(dp2pxToInt(150f),dp2pxToInt(150f),Bitmap.Config.ARGB_8888)


    init {
        val canvas = Canvas(descBitmap)
        paint.color = Color.parseColor("#F42042")
        canvas.drawOval(dp2px(50f), dp2px(0f), dp2px(150f), dp2px(100f), paint)

        paint.color = Color.parseColor("#D8A833")
        canvas.setBitmap(srcBitmap)
        canvas.drawRect(dp2px(0f), dp2px(50f), dp2px(100f), dp2px(150f), paint)

    }

    override fun onDraw(canvas: Canvas) {


//        canvas.drawLine(100f, 100f, 200f, 200f, paint)

//        canvas.drawPath(path, paint)
//        canvas.drawCircle(400f,400f, dp2px(50f), paint)
        var count = canvas.saveLayer(dp2px(150f), dp2px(50f), dp2px(300f), dp2px(200f), paint)

        //DST
        canvas.drawBitmap(descBitmap,dp2px(150f), dp2px(50f), paint)
        paint.xfermode = xfermode

        //SRC
        canvas.drawBitmap(srcBitmap,dp2px(150f), dp2px(50f), paint)

        paint.xfermode = null

        canvas.restoreToCount(count)
    }

}

fun View.dp2px(value: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.resources.displayMetrics)
fun View.dp2pxToInt(value: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.resources.displayMetrics).toInt()
