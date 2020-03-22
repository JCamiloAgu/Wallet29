package com.camilo.wallet29.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.camilo.wallet29.R
import com.camilo.wallet29.adapters.AuthAdapter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pager.adapter = AuthAdapter(supportFragmentManager, pager)

    }
}
