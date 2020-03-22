package com.camilo.wallet29.ui.login.fragments

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.camilo.wallet29.R
import com.camilo.wallet29.ui.login.interpolator.BounceOvershootInterpolator
import kotlinx.android.synthetic.main.fragment_sign_up.view.*

class SignUpFragment : AuthFragment() {

    private lateinit var views: List<View>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_sign_up, container, false)

        controller = root.controller
        logo = root.focus_hider
        parent = root.parentSignUp

        first = root.imgFacebook
        second = root.second
        last = root.last

        views = listOf(
            root.email_input_edit,
            root.password_input_edit,
            root.confirm_password_input_edit,
            parent, first, second, last
        )

        controller.text = mergeColoredText(
            getString(R.string.or), getString(R.string.log_in_or_label),
            ContextCompat.getColor(context!!, R.color.white_trans),
            ContextCompat.getColor(context!!, R.color.color_text)
        )

        initView()

        controller.setOnClickListener {
            makeTransition()
        }

        return root
    }

    override fun hideItems() {
        views.forEach {
            it.visibility = View.GONE
        }
    }

    override fun showItems() {
        views.forEach {
            it.visibility = View.VISIBLE
        }
    }

    override fun clearFocus() {
        for (view in views) {
            view.clearFocus()
        }
    }

    override fun fireAnimation() {
        val offsetX =
            parent.width - (parent.width - first.left + resources.getDimension(R.dimen.option_size))
        val firstAnimator =
            ObjectAnimator.ofFloat(first, View.TRANSLATION_X, 0f)
        val secondAnimator =
            ObjectAnimator.ofFloat(second, View.TRANSLATION_X, 0f)
        val lastAnimator =
            ObjectAnimator.ofFloat(last, View.TRANSLATION_X, 0f)
        val buttonAnimator = ObjectAnimator.ofFloat(
            controller,
            View.TRANSLATION_X,
            controller.translationX
        )

        first.translationX = -offsetX
        second.translationX = -offsetX
        last.translationX = -offsetX
        controller.translationX = (-controller.width).toFloat()

        buttonAnimator.interpolator = BounceOvershootInterpolator(4f)

        val buttonSet = AnimatorSet().apply {
            playTogether(firstAnimator, secondAnimator, lastAnimator)
            interpolator = BounceOvershootInterpolator(2f)
        }

        AnimatorSet().apply {
            startDelay = 500
            playTogether(buttonSet, buttonAnimator)
        }.start()
    }
}