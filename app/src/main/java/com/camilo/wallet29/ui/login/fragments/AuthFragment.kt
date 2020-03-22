package com.camilo.wallet29.ui.login.fragments

import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import java.util.*

abstract class AuthFragment : Fragment() {

    private var callback: Callback? = null

    lateinit var controller: TextView
    lateinit var parent: ViewGroup
    lateinit var first: View
    lateinit var second: View
    lateinit var last: View
    lateinit var logo: View

    internal fun setCallback(callback: Callback) {
        this.callback = callback
    }

    abstract fun fireAnimation()

    abstract fun clearFocus()

    internal interface Callback {
        fun remove(fragment: AuthFragment?)
    }

    open fun mergeColoredText(
        leftPart: String,
        rightPart: String,
        leftColor: Int,
        rightColor: Int
    ): SpannableStringBuilder? {
        val builder = SpannableStringBuilder()
        val leftPartSpannable = SpannableString(leftPart.toUpperCase(Locale.ROOT))
        val rightPartSpannable = SpannableString(rightPart.toUpperCase(Locale.ROOT))

        leftPartSpannable.setSpan(ForegroundColorSpan(leftColor), 0, leftPart.length, 0)
        rightPartSpannable.setSpan(ForegroundColorSpan(rightColor), 0, rightPart.length, 0)

        return builder.append(leftPartSpannable).append("  ").append(rightPartSpannable)
    }

    fun initView() {
        KeyboardVisibilityEvent.setEventListener(activity) { isOpen: Boolean ->
            val scale = if (isOpen) 0.75f else 1f
            ViewCompat.animate(logo)
                .scaleX(scale)
                .scaleY(scale)
                .setDuration(300)
                .start()
            if (!isOpen)
                clearFocus()
        }
    }

    fun makeTransition() {
        if (callback != null) {
            callback!!.remove(this)
        }
    }

    abstract fun hideItems()
    abstract fun showItems()
}
