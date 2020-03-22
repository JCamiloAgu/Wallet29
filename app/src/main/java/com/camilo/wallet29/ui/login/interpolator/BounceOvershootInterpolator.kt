package com.camilo.wallet29.ui.login.interpolator

import android.view.animation.BounceInterpolator
import android.view.animation.Interpolator
import android.view.animation.OvershootInterpolator


class BounceOvershootInterpolator(tension: Float) :
    Interpolator {
    private val overshootInterpolator: OvershootInterpolator = OvershootInterpolator(tension)
    private val bounceInterpolator: BounceInterpolator = BounceInterpolator()

    override fun getInterpolation(input: Float): Float {
        return if (input > .99f) bounceInterpolator.getInterpolation(input) else overshootInterpolator.getInterpolation(
            input
        )
    }

}