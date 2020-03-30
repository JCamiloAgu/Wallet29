package com.camilo.wallet29

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.camilo.wallet29.local_data.entity.AccountWalletEntity
import com.camilo.wallet29.ui.debs.DebsActivity
import com.camilo.wallet29.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_activity_main.*
import kotlinx.android.synthetic.main.nav_header_activity_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    val viewModel: MainViewModel by viewModels()

    var items: MutableLiveData<List<AccountWalletEntity?>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        setUpDrawerLayout()

        setUpNavigationBottomBar()
    }


    private fun setUpNavigationBottomBar() {
        customBottomBar.post {
            val margins = (fabMain.layoutParams as RelativeLayout.LayoutParams).apply {
                bottomMargin = (customBottomBar.height - 10)
            }
            fabMain.layoutParams = margins
        }

        val navController = findNavController(R.id.nav_host_fragment_bottom_nav)
        customBottomBar.setupWithNavController(navController)
    }

    private fun setUpDrawerLayout() {

        val header = nav_view.getHeaderView(0)
        header.txtGoLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            drawer_layout.closeDrawer(nav_view)
        }

        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            findViewById(R.id.drawer_layout),
            R.string.Open,
            R.string.Close
        )
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_categories -> Toast.makeText(this, "Category", Toast.LENGTH_SHORT).show()
                R.id.nav_debts -> {
                    val intent = Intent(this, DebsActivity::class.java)
                    startActivity(intent)
                    drawer_layout.closeDrawer(nav_view)
                }
                R.id.nav_about -> Toast.makeText(this, "About", Toast.LENGTH_SHORT).show()
            }

            true

        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START))
            drawer_layout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(item!!)
    }
}
