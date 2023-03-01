package com.tracker.runner.room.converters

import android.graphics.Bitmap

interface RunnerConverter {

    fun convertFromBitmap(bitmap: Bitmap): ByteArray

    fun convertToBitmap(byteArray: ByteArray): Bitmap
}