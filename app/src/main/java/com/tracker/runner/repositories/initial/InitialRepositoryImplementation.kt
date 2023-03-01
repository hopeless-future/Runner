package com.tracker.runner.repositories.initial

import android.content.Context
import androidx.preference.PreferenceManager
import com.tracker.runner.utils.Constants.SHARED_PREFERENCES_NAME_KEY
import com.tracker.runner.utils.Constants.SHARED_PREFERENCES_WEIGHT_KEY
import javax.inject.Inject

class InitialRepositoryImplementation @Inject constructor(): InitialRepository {

    override fun saveUserInfo(context: Context, name: String, weight: String) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val sharedPreferencesEditor = sharedPreferences.edit()
        sharedPreferencesEditor.putString(SHARED_PREFERENCES_NAME_KEY, name)
        sharedPreferencesEditor.putString(SHARED_PREFERENCES_WEIGHT_KEY, weight)
        sharedPreferencesEditor.apply()
    }
}