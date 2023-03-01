package com.tracker.runner.room.dao

import androidx.room.*
import com.tracker.runner.room.entities.RunInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RunnerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewRunInfoEntity(runInfoEntity: RunInfoEntity)

    @Delete
    suspend fun deleteRunInfoEntity(runInfoEntity: RunInfoEntity)

    @Query("SELECT * FROM RunInfoEntity ORDER BY runningTimeStarted DESC")
    fun getAllSortedRunInfoEntitiesByRunningTimeStarted(): Flow<List<RunInfoEntity>>

    @Query("SELECT * FROM RunInfoEntity ORDER BY averageSpeed DESC")
    fun getAllSortedRunInfoEntitiesByAverageSpeed(): Flow<List<RunInfoEntity>>

    @Query("SELECT * FROM RunInfoEntity ORDER BY runningDistance DESC")
    fun getAllSortedRunInfoEntitiesByRunningDistance(): Flow<List<RunInfoEntity>>

    @Query("SELECT * FROM RunInfoEntity ORDER BY runningTime DESC")
    fun getAllSortedRunInfoEntitiesByRunningTime(): Flow<List<RunInfoEntity>>

    @Query("SELECT * FROM RunInfoEntity ORDER BY caloriesLost DESC")
    fun getAllSortedRunInfoEntitiesByCaloriesLost(): Flow<List<RunInfoEntity>>

    @Query("SELECT SUM(runningTime) FROM RunInfoEntity")
    fun getTotalRunningTimeFromDatabase(): Flow<Long>

    @Query("SELECT AVG(averageSpeed) FROM RunInfoEntity")
    fun getTotalAverageSpeedFromDatabase(): Flow<Float>

    @Query("SELECT SUM(runningDistance) FROM RunInfoEntity")
    fun getTotalRunningDistanceFromDatabase(): Flow<Int>

    @Query("SELECT SUM(caloriesLost) FROM RunInfoEntity")
    fun getTotalCaloriesLostFromDatabase(): Flow<Int>
}