package com.tracker.runner.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tracker.runner.repositories.runner.RunnerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(private val runnerRepository: RunnerRepository): ViewModel() {

    val totalRunningTimeFromDatabase = runnerRepository.getTotalRunningTimeFromDatabase().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0L)

    val totalAverageSpeedFromDatabase = runnerRepository.getTotalAverageSpeedFromDatabase().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0f)

    val totalRunningDistanceFromDatabase = runnerRepository.getTotalRunningDistanceFromDatabase().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)

    val totalCaloriesLostFromDatabase = runnerRepository.getTotalCaloriesLostFromDatabase().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)
}