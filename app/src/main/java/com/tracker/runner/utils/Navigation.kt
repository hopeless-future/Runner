package com.tracker.runner.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

fun Fragment.navigateToAnotherFragment(destination: Int) {
    findNavController().navigate(destination)
}

fun Activity.navigateToAnotherFragment(navController: NavController, destination: Int) {
    navController.navigate(destination)
}