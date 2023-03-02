package com.tracker.runner.viewmodels

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tracker.runner.repositories.runner.RunnerRepository
import com.tracker.runner.room.entities.RunInfoEntity
import com.tracker.runner.utils.NetworkState
import com.tracker.runner.utils.checkConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val runnerRepository: RunnerRepository): ViewModel() {

    private val _networkState: MutableStateFlow<NetworkState> = MutableStateFlow(NetworkState.DefaultConnection)
    val networkState = _networkState.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkDeviceConnection(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            if (checkConnection(context)) _networkState.emit(NetworkState.HasInternetConnection)
            else _networkState.emit(NetworkState.NoInternetConnection)
        }
    }

    fun insertNewRunInfoEntity(runInfoEntity: RunInfoEntity) { viewModelScope.launch(Dispatchers.IO) { runnerRepository.insertNewRunInfoEntity(runInfoEntity) } }

    fun deleteRunInfoEntity(runInfoEntity: RunInfoEntity) { viewModelScope.launch(Dispatchers.IO) { runnerRepository.deleteRunInfoEntity(runInfoEntity) } }

    val allSortedRunInfoEntitiesByRunningTimeStarted = runnerRepository.getAllSortedRunInfoEntitiesByRunningTimeStarted().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val allSortedRunInfoEntitiesByAverageSpeed = runnerRepository.getAllSortedRunInfoEntitiesByAverageSpeed().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val allSortedRunInfoEntitiesByRunningDistance = runnerRepository.getAllSortedRunInfoEntitiesByRunningDistance().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val allSortedRunInfoEntitiesByRunningTime = runnerRepository.getAllSortedRunInfoEntitiesByRunningTime().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val allSortedRunInfoEntitiesByCaloriesLost = runnerRepository.getAllSortedRunInfoEntitiesByCaloriesLost().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}