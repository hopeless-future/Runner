package com.tracker.runner.utils.validation

sealed class ValidationStatus {
    object Success: ValidationStatus()
    data class Error(val errorMessage: String): ValidationStatus()
    object Undefined: ValidationStatus()
}
