package com.example.fbla_project_s.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.fbla_project_s.GlobalVars
import com.example.fbla_project_s.R
import com.example.fbla_project_s.databinding.NavigationSidebarScreenBinding
import com.example.fbla_project_s.ui.settings.SettingsActivity
import com.example.fbla_project_s.ui.settings.SettingsFragment


class HomeActivity2 : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: NavigationSidebarScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NavigationSidebarScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarHome2.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home2)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //set more button to every page
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_calendar,
                R.id.nav_messages,
                R.id.nav_settings,
                R.id.nav_calendar,
                R.id.nav_about_us,
                R.id.nav_addEvent
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_activity2, menu)
        val username: TextView = findViewById(R.id.display_username)
        val email: TextView = findViewById(R.id.display_email)
        var currentUsername = GlobalVars.Companion.username
        var currentEmail = GlobalVars.Companion.email
        username.text = currentUsername
        email.text = currentEmail
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home2)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}