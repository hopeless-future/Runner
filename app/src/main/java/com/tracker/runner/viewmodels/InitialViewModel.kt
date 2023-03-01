package com.tracker.runner.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tracker.runner.repositories.initial.InitialRepository
import com.tracker.runner.utils.validation.UserValidationState
import com.tracker.runner.utils.validation.ValidationStatus
import com.tracker.runner.utils.validation.checkValidationName
import com.tracker.runner.utils.validation.checkValidationWeight
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitialViewModel @Inject constructor(private val initialRepository: InitialRepository): ViewModel() {

    private val _userValidationFlow: MutableSharedFlow<UserValidationState> = MutableSharedFlow()
    val userValidationFlow = _userValidationFlow.asSharedFlow()

    private val _navigationFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val navigationFlow = _navigationFlow.asStateFlow()

    fun saveUserInfo(context: Context, name: String, weight: String) {
        if (isCorrectedEditTextsInput(name, weight)) {
            initialRepository.saveUserInfo(context, name, weight)
            _navigationFlow.value = true
        }
        else {
            viewModelScope.launch(Dispatchers.IO) {
                val userValidationState = UserValidationState(checkValidationName(name), checkValidationWeight(weight))
                _userValidationFlow.emit(userValidationState)
            }
        }
    }

    private fun isCorrectedEditTextsInput(name: String, weight: String): Boolean {
        val nameValidationStatus = checkValidationName(name)
        val weightValidationStatus = checkValidationWeight(weight)
        return nameValidationStatus is ValidationStatus.Success && weightValidationStatus is ValidationStatus.Success
    }
}