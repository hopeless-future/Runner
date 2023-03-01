package com.tracker.runner.di.runner_module

import android.app.Application
import androidx.room.Room
import com.tracker.runner.repositories.runner.RunnerRepository
import com.tracker.runner.repositories.runner.RunnerRepositoryImplementation
import com.tracker.runner.room.converters.RunnerConverterImplementation
import com.tracker.runner.room.database.RunnerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RunnerModule {

    @Provides
    @Singleton
    fun provideRunnerDatabase(applicationContext: Application): RunnerDatabase = Room.databaseBuilder(applicationContext, RunnerDatabase::class.java, "runner_db")
        .addTypeConverter(RunnerConverterImplementation()).build()

    @Provides
    @Singleton
    fun provideRunnerRepository(runnerDatabase: RunnerDatabase): RunnerRepository = RunnerRepositoryImplementation(runnerDatabase)
}