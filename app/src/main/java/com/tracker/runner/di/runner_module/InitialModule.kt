package com.tracker.runner.di.runner_module

import com.tracker.runner.repositories.initial.InitialRepository
import com.tracker.runner.repositories.initial.InitialRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object InitialModule {

    @Provides
    @ViewModelScoped
    fun provideInitialRepository(): InitialRepository = InitialRepositoryImplementation()

}