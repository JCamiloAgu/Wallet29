package com.camilo.wallet29.adapters

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.camilo.wallet29.custom_views.AnimatedViewPager
import com.camilo.wallet29.ui.login.fragments.AuthFragment
import com.camilo.wallet29.ui.login.fragments.LogInFragment
import com.camilo.wallet29.ui.login.fragments.SignUpFragment

import com.camilo.wallet29.ui.login.transformer.FlipTransformer


class AuthAdapter(
    manager: FragmentManager?,
    private val pager: AnimatedViewPager
) :
    FragmentStatePagerAdapter(manager!!), AuthFragment.Callback {

    private var signUp: AuthFragment? = null
    private var logIn: AuthFragment? = null
    private val transformer: FlipTransformer = FlipTransformer(160)

    init {
        pager.mDuration = 500
        pager.setPageTransformer(true, transformer)
    }

    override fun getItem(position: Int): AuthFragment {
        if (position == 0) {
            if (logIn == null)
                logIn = LogInFragment()
            logIn!!.setCallback(this)
            return logIn!!
        } else if (signUp == null) {
            signUp = SignUpFragment()
            signUp!!.setCallback(this)
        }
        return signUp!!
    }

    override fun getCount(): Int = 2

    override fun remove(fragment: AuthFragment?) {
        if (logIn === fragment) {
            transformer.setMovingForward(true)
            pager.setCurrentItem(1, true)
            signUp!!.fireAnimation()
            logIn!!.hideItems()
            signUp!!.showItems()
        } else {
            transformer.setMovingForward(false)
            pager.setCurrentItem(0, true)
            logIn!!.fireAnimation()
            signUp!!.hideItems()
            logIn!!.showItems()
        }
    }
}
