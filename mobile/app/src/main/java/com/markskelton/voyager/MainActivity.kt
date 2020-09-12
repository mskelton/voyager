package com.markskelton.voyager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.markskelton.voyager.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    // Top level destinations should be added to this list
    private val destinations = setOf(
        R.id.nav_home,
        R.id.nav_projects,
        R.id.nav_vehicles
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create an app bar configuration which includes the top level destinations we defined.
        // This is required as the default app bar configuration only uses the start destination
        // as a top level destination but in our case we want all the menu items to be considered
        // as top level destinations.
        appBarConfiguration = AppBarConfiguration(destinations, binding.appDrawer)

        // Setup toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        setSupportActionBar(toolbar)

        // Setup drawer icon and back button
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Connect the nav view with the nav controller to handle menu item clicks.
        binding.navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}
