package com.tracker.runner.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tracker.runner.repositories.runner.RunnerRepository
import com.tracker.runner.room.entities.RunInfoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val runnerRepository: RunnerRepository): ViewModel() {

    fun insertNewRunInfoEntity(runInfoEntity: RunInfoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            runnerRepository.insertNewRunInfoEntity(runInfoEntity)
        }
    }

    fun deleteRunInfoEntity(runInfoEntity: RunInfoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            runnerRepository.deleteRunInfoEntity(runInfoEntity)
        }
    }

    val allSortedRunInfoEntitiesByRunningTimeStarted = runnerRepository.getAllSortedRunInfoEntitiesByRunningTimeStarted().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val allSortedRunInfoEntitiesByAverageSpeed = runnerRepository.getAllSortedRunInfoEntitiesByAverageSpeed().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val allSortedRunInfoEntitiesByRunningDistance = runnerRepository.getAllSortedRunInfoEntitiesByRunningDistance().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val allSortedRunInfoEntitiesByRunningTime = runnerRepository.getAllSortedRunInfoEntitiesByRunningTime().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val allSortedRunInfoEntitiesByCaloriesLost = runnerRepository.getAllSortedRunInfoEntitiesByCaloriesLost().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}