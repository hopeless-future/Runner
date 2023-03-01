package com.tracker.runner.repositories.initial

import android.content.Context

interface InitialRepository {

    fun saveUserInfo(context: Context, name: String, weight: String)
}