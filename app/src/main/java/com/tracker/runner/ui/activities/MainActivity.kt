package com.tracker.runner.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.tracker.runner.R
import com.tracker.runner.databinding.ActivityMainBinding
import com.tracker.runner.utils.checkFirstLaunchApp
import com.tracker.runner.utils.navigateToAnotherFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavHostFragment()
        setupNavController()
        setupBottomNavigationViewWithNavController()
        handleBottomNavigationViewVisibility()
        checkIsFirstLaunchApp()
    }

    private fun navigateToMainFragment() {
        navigateToAnotherFragment(navController, R.id.mainFragment)
    }

    private fun checkIsFirstLaunchApp() {
        when (checkFirstLaunchApp(this)) {
            true -> return
            else -> navigateToMainFragment()
        }
    }

    private fun setupNavHostFragment() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
    }

    private fun setupNavController() {
        navController = navHostFragment.findNavController()
    }

    private fun setupBottomNavigationViewWithNavController() {
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun hideBottomNavigationView() {
        binding.bottomNavigationView.visibility = View.GONE
    }

    private fun showBottomNavigationView() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    private fun handleBottomNavigationViewVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.initialFragment, R.id.runningFragment -> hideBottomNavigationView()
                else -> showBottomNavigationView()
            }
        }
    }
}