package com.tracker.runner.utils

import android.os.Build

fun checkIsDeviceLowerThan(androidVersion: Int) = Build.VERSION.SDK_INT < androidVersion