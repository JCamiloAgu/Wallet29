package com.camilo.wallet29.ui.debs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.camilo.wallet29.R

class DebsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debs)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
