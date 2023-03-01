package com.tracker.runner.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tracker.runner.room.converters.RunnerConverterImplementation
import com.tracker.runner.room.dao.RunnerDao
import com.tracker.runner.room.entities.RunInfoEntity

@Database(entities = [RunInfoEntity::class], version = 1)
@TypeConverters(RunnerConverterImplementation::class)
abstract class RunnerDatabase: RoomDatabase() {

    abstract fun runningDao(): RunnerDao
}