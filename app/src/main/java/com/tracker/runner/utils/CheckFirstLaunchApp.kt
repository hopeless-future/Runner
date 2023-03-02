package com.tracker.runner.utils

import android.content.Context
import androidx.preference.PreferenceManager

fun checkFirstLaunchApp(context: Context): Boolean {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    return sharedPreferences.all.isEmpty()
}