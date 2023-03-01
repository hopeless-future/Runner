package com.tracker.runner.utils

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.navigateToAnotherFragment(destination: Int) {
    findNavController().navigate(destination)
}