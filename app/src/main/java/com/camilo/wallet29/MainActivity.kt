package com.camilo.wallet29

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        spinnerAccounts.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            arrayOf("Todas las cuentas", "dos", "y tres")
        )
//        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
//        val navView2: NavigationView = findViewById(R.id.nav_view)
//        val navController2 = findNavController(R.id.nav_host_fragment2)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        appBarConfiguration = AppBarConfiguration(setOf(
//            R.id.nav_categories, R.id.nav_debts, R.id.nav_configurations), drawerLayout)
//        setupActionBarWithNavController(navController2, appBarConfiguration)
//        navView2.setupWithNavController(navController2)

        val navView: BottomNavigationView = findViewById(R.id.customBottomBar)
        navView.post {
            val margins = (fabMain.layoutParams as RelativeLayout.LayoutParams).apply {
                bottomMargin = (customBottomBar.height - 10)
            }
            fabMain.layoutParams = margins
        }

        val navController = findNavController(R.id.nav_host_fragment_bottom_nav)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(navGraph = navController.graph)
        navView.setupWithNavController(navController)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_bottom_nav)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START))
            drawer_layout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }
}
