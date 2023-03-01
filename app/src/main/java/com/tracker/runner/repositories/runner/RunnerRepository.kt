package com.tracker.runner.repositories.runner

import com.tracker.runner.room.entities.RunInfoEntity
import kotlinx.coroutines.flow.Flow

interface RunnerRepository {

    suspend fun insertNewRunInfoEntity(runInfoEntity: RunInfoEntity)

    suspend fun deleteRunInfoEntity(runInfoEntity: RunInfoEntity)

    fun getAllSortedRunInfoEntitiesByRunningTimeStarted(): Flow<List<RunInfoEntity>>

    fun getAllSortedRunInfoEntitiesByAverageSpeed(): Flow<List<RunInfoEntity>>

    fun getAllSortedRunInfoEntitiesByRunningDistance(): Flow<List<RunInfoEntity>>

    fun getAllSortedRunInfoEntitiesByRunningTime(): Flow<List<RunInfoEntity>>

    fun getAllSortedRunInfoEntitiesByCaloriesLost(): Flow<List<RunInfoEntity>>

    fun getTotalRunningTimeFromDatabase(): Flow<Long>

    fun getTotalAverageSpeedFromDatabase(): Flow<Float>

    fun getTotalRunningDistanceFromDatabase(): Flow<Int>

    fun getTotalCaloriesLostFromDatabase(): Flow<Int>
}