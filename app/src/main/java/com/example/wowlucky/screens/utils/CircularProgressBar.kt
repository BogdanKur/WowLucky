package com.example.wowlucky.screens.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.wowlucky.R

class CircularProgressBar(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var drawableOuterList: MutableList<Drawable> = MutableList(89) {
        ContextCompat.getDrawable(context, R.drawable.progress_element_1)!!
    }

    private var currentDrawableIndex = 0
    var elementToUpdateIndex = 0

    private var outerProgress = 0f
    var totalElements = drawableOuterList.size

    fun setOuterProgress(progress: Float) {
        outerProgress = progress
        invalidate()
    }

    fun updateDrawableAtIndex(index: Int, newDrawable: Drawable) {
        if (index in 0 until totalElements) {
            drawableOuterList[index] = newDrawable
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()
        val centerX = width / 2
        val centerY = height / 2

        drawCircularElements(
            canvas,
            drawableOuterList,
            centerX,
            centerY,
            width / 2.5f,
            90,
            totalElements,
            outerProgress
        )
    }

    private fun drawCircularElements(
        canvas: Canvas,
        drawables: List<Drawable>,
        centerX: Float,
        centerY: Float,
        radius: Float,
        drawableRotation: Int,
        totalElements: Int,
        progress: Float
    ) {
        val angleStep = 360f / totalElements
        val drawableSize = drawables[0].intrinsicWidth

        for (i in 0 until totalElements) {
            val angle = angleStep * i

            val radian = Math.toRadians(angle.toDouble())
            val elementX = (centerX + radius * Math.cos(radian)).toFloat()
            val elementY = (centerY + radius * Math.sin(radian)).toFloat()

            val drawable = drawables[i]
            drawable.setBounds(
                (elementX - drawableSize / 2).toInt(),
                (elementY - drawableSize / 2).toInt(),
                (elementX + drawableSize / 2).toInt(),
                (elementY + drawableSize / 2).toInt()
            )

            val saveCount = canvas.save()
            canvas.rotate(angle + drawableRotation, elementX, elementY)

            drawable.draw(canvas)
            canvas.restoreToCount(saveCount)
        }
    }
}
