package com.camilo.wallet29.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.widget.Scroller
import androidx.viewpager.widget.ViewPager


class AnimatedViewPager : ViewPager {
    var mDuration = 0

    constructor(context: Context?) : super(context!!) {
        postInitViewPager()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!,
        attrs
    ) {
        postInitViewPager()
    }

    private var mScroller: ScrollerCustomDuration? = null
    private fun postInitViewPager() {
        try {
            val scroller =
                ViewPager::class.java.getDeclaredField("mScroller")

            scroller.isAccessible = true

            mScroller = ScrollerCustomDuration(
                context,
                DecelerateInterpolator()
            )

            scroller[this] = mScroller
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return false
    }

    fun setScrollDurationFactor(scrollFactor: Double) {
        mScroller!!.setScrollDurationFactor(scrollFactor)
    }

    inner class ScrollerCustomDuration(
        context: Context?,
        interpolator: Interpolator?
    ) : Scroller(context, interpolator) {
        private var mScrollFactor = 1.0

        fun setScrollDurationFactor(scrollFactor: Double) {
            mScrollFactor = scrollFactor
        }

        override fun startScroll(
            startX: Int,
            startY: Int,
            dx: Int,
            dy: Int,
            duratio: Int
        ) {
            super.startScroll(startX, startY, dx, dy, mDuration)
        }

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
            super.startScroll(startX, startY, dx, dy, mDuration)
        }
    }
}