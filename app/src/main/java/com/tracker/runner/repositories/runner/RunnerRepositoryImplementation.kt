package com.tracker.runner.repositories.runner

import com.tracker.runner.room.database.RunnerDatabase
import com.tracker.runner.room.entities.RunInfoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RunnerRepositoryImplementation @Inject constructor(private val runnerDatabase: RunnerDatabase):
    RunnerRepository {

    override suspend fun insertNewRunInfoEntity(runInfoEntity: RunInfoEntity) {
        runnerDatabase.runningDao().insertNewRunInfoEntity(runInfoEntity)
    }

    override suspend fun deleteRunInfoEntity(runInfoEntity: RunInfoEntity) {
        runnerDatabase.runningDao().deleteRunInfoEntity(runInfoEntity)
    }

    override fun getAllSortedRunInfoEntitiesByRunningTimeStarted(): Flow<List<RunInfoEntity>> {
        return flow {
            try {
                runnerDatabase.runningDao().getAllSortedRunInfoEntitiesByRunningTimeStarted()
            } catch (e: Exception) { e.printStackTrace() }
        }
    }

    override fun getAllSortedRunInfoEntitiesByAverageSpeed(): Flow<List<RunInfoEntity>> {
        return flow {
            try {
                runnerDatabase.runningDao().getAllSortedRunInfoEntitiesByAverageSpeed()
            } catch (e: Exception) { e.printStackTrace() }
        }
    }

    override fun getAllSortedRunInfoEntitiesByRunningDistance(): Flow<List<RunInfoEntity>> {
        return flow {
            try {
                runnerDatabase.runningDao().getAllSortedRunInfoEntitiesByRunningDistance()
            } catch (e: Exception) { e.printStackTrace() }
        }
    }

    override fun getAllSortedRunInfoEntitiesByRunningTime(): Flow<List<RunInfoEntity>> {
        return flow {
            try {
                runnerDatabase.runningDao().getAllSortedRunInfoEntitiesByRunningTime()
            } catch (e: Exception) { e.printStackTrace() }
        }
    }

    override fun getAllSortedRunInfoEntitiesByCaloriesLost(): Flow<List<RunInfoEntity>> {
        return flow {
            try {
                runnerDatabase.runningDao().getAllSortedRunInfoEntitiesByCaloriesLost()
            } catch (e: Exception) { e.printStackTrace() }
        }
    }

    override fun getTotalRunningTimeFromDatabase(): Flow<Long> {
        return flow {
            try {
                runnerDatabase.runningDao().getTotalRunningTimeFromDatabase()
            } catch (e: Exception) { e.printStackTrace() }
        }
    }

    override fun getTotalAverageSpeedFromDatabase(): Flow<Float> {
        return flow {
            try {
                runnerDatabase.runningDao().getTotalAverageSpeedFromDatabase()
            } catch (e: Exception) { e.printStackTrace() }
        }
    }

    override fun getTotalRunningDistanceFromDatabase(): Flow<Int> {
        return flow {
            try {
                runnerDatabase.runningDao().getTotalRunningDistanceFromDatabase()
            } catch (e: Exception) { e.printStackTrace() }
        }
    }

    override fun getTotalCaloriesLostFromDatabase(): Flow<Int> {
        return flow {
            try {
                runnerDatabase.runningDao().getTotalCaloriesLostFromDatabase()
            } catch (e: Exception) { e.printStackTrace() }
        }
    }

}