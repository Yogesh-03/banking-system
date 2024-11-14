package com.news2day.yadavbankingservices.utils

import android.graphics.*
import android.graphics.drawable.Drawable

class TextDrawable(private val text:String) : Drawable() {
    private val paint: Paint = Paint()

    init {
        paint.color = Color.WHITE
        paint.textSize = 50f
        paint.isAntiAlias = true
        paint.textAlign = Paint.Align.CENTER
    }

    override fun draw(canvas: Canvas) {
        val bounds = bounds
        val x = bounds.width() / 2f
        val y = bounds.height() / 2f - (paint.descent() + paint.ascent()) / 2
        canvas.drawText(text, x, y, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }
}