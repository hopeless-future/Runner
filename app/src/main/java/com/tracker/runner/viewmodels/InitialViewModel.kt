package com.tracker.runner.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tracker.runner.repositories.initial.InitialRepository
import com.tracker.runner.utils.validation.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitialViewModel @Inject constructor(private val initialRepository: InitialRepository): ViewModel() {

    private val _nameValidation: MutableLiveData<NameValidationState> = MutableLiveData()
    val nameValidation: LiveData<NameValidationState> = _nameValidation

    private val _weightValidation: MutableLiveData<WeightValidationState> = MutableLiveData()
    val weightValidation: LiveData<WeightValidationState> = _weightValidation

    fun checkNameValidation(name: String) {
        when (checkValidationName(name)) {
            is ValidationStatus.Success -> _nameValidation.value = NameValidationState(ValidationStatus.Success, name)
            is ValidationStatus.Error -> _nameValidation.value = NameValidationState(checkValidationName(name), name)
            else -> _nameValidation.value = NameValidationState(ValidationStatus.Undefined, null)
        }
    }

    fun checkWeightValidation(weight: String) {
        when (checkValidationWeight(weight)) {
            is ValidationStatus.Success -> _weightValidation.value = WeightValidationState(ValidationStatus.Success, weight)
            is ValidationStatus.Error -> _weightValidation.value = WeightValidationState(checkValidationWeight(weight), weight)
            else -> _weightValidation.value = WeightValidationState(ValidationStatus.Undefined, null)
        }
    }

    fun saveUserInfo(context: Context, name: String, weight: String) {
        viewModelScope.launch(Dispatchers.IO) {
            initialRepository.saveUserInfo(context, name, weight)
        }
    }
}