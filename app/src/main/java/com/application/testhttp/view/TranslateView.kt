package com.application.testhttp.view

import android.animation.ArgbEvaluator
import android.animation.IntEvaluator
import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.Choreographer
import android.view.View
import androidx.core.graphics.toColorInt
import androidx.core.graphics.withClip
import androidx.core.graphics.withSave
import com.application.testhttp.R
import com.hencoder.text.dp

class TranslateView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val BITMAP_SIZE = 200.dp
    private val BITMAP_PADDING = 100.dp

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = "#FF0000".toColorInt()
    }

    val bitmap = getAvatar(200.dp.toInt())

    val path = Path()
    val camera =  Camera()

    init {
        camera.rotateX(30f)
        camera.setLocation(0f,0f, -6 * resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {


//        path.addOval(0f, 0f, 200.dp, 200.dp, Path.Direction.CCW)
//        canvas.clipPath(path)

//        canvas.scale(1f,2f)

//        canvas.translate(0f, 100.dp)

//        canvas.drawBitmap(bitmap,0f,0f, paint)
        //上半部分
        canvas.withSave {
//            canvas.translate(BITMAP_PADDING + BITMAP_SIZE / 2, BITMAP_PADDING + BITMAP_SIZE / 2)
            canvas.clipRect(BITMAP_PADDING, BITMAP_PADDING, BITMAP_PADDING + BITMAP_SIZE , BITMAP_PADDING + BITMAP_SIZE / 2)
//            canvas.translate()
//            canvas.translate(-(BITMAP_PADDING + BITMAP_SIZE / 2), -(BITMAP_PADDING + BITMAP_SIZE / 2))
            canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
        }

        //下半部分

        canvas.withSave {
            canvas.translate(BITMAP_PADDING + BITMAP_SIZE / 2, BITMAP_PADDING + BITMAP_SIZE / 2)
            camera.applyToCanvas(canvas)
            canvas.clipRect(-BITMAP_SIZE / 2, 0f, BITMAP_SIZE / 2 , BITMAP_SIZE / 2)
            canvas.translate(-(BITMAP_PADDING + BITMAP_SIZE / 2), -(BITMAP_PADDING + BITMAP_SIZE / 2))
            canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
        }
//
//        canvas.translate(BITMAP_PADDING + BITMAP_SIZE / 2, BITMAP_PADDING + BITMAP_SIZE / 2)
//       camera.applyToCanvas(canvas)
//        canvas.translate(-(BITMAP_PADDING + BITMAP_SIZE / 2), -(BITMAP_PADDING + BITMAP_SIZE / 2))
//       canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
        var ofFloat = ObjectAnimator.ofFloat(this, "scaleX", 0f, 1f)
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

    class PointFEvaluator: TypeEvaluator<PointF> {
        override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
            val startX = startValue.x
            val endX = endValue.x
            val currentX = startX + (endX - startX) * fraction
            val startY = startValue.y
            val endY = endValue.y
            val currentY = startY + (endY - startY) * fraction
            return PointF(currentX, currentY)
        }


    }
}