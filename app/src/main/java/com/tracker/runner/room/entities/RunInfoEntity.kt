package com.tracker.runner.room.entities

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RunInfoEntity(
    var runningImage: Bitmap? = null,
    var runningTimeStarted: Long = 0L,
    var averageSpeed: Float = 0f,
    var runningDistance: Int = 0,
    var runningTime: Long = 0L,
    var caloriesLost: Int = 0
) {
    @PrimaryKey(autoGenerate = true)  // we don't need the primary key in constructor
    var id: Int? = null
}
