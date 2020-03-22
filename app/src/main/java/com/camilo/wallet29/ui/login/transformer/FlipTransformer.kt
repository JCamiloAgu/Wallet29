package com.camilo.wallet29.ui.login.transformer

import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.tan

class FlipTransformer constructor(
    private val degrees: Int,
    private val minAlpha: Float = 0.7f
) :
    ViewPager.PageTransformer {
    private var movingForward = true
    private val distanceToCentreFactor: Float =
        tan(Math.toRadians(degrees / 2.toDouble()))
            .toFloat() / 2

    override fun transformPage(
        view: View,
        position: Float
    ) {
        val pageWidth = view.width
        val pageHeight = view.height

        view.pivotX = pageWidth.toFloat() / 2
        view.pivotY = (pageHeight + pageWidth * distanceToCentreFactor)

        if (position < -1) {
            view.rotation = 0f
            view.alpha = 0f
        } else if (position <= 1) {
            view.translationX = -position * pageWidth
            if (movingForward) {
                if (position >= 0) {
                    view.rotation = position * (180 - degrees)

                    view.alpha = minAlpha.coerceAtLeast(1 - abs(position) / 3)
                } else {
                    view.alpha = 1 + position
                }
            } else if (position <= 0) {
                view.rotation = position * (180 - degrees)

                view.alpha = max(minAlpha, 1 - abs(position) / 3)
            } else {
                view.alpha = 1 - position
            }
        } else {
            view.rotation = 0f
            view.alpha = 0f
        }
    }

    fun setMovingForward(movingForward: Boolean) {
        this.movingForward = movingForward
    }

}
