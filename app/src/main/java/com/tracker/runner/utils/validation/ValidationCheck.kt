package com.tracker.runner.utils.validation

import com.tracker.runner.utils.Constants.VALIDATION_MAX_NAME_LENGTH
import com.tracker.runner.utils.Constants.VALIDATION_MAX_WEIGHT_VALUE
import com.tracker.runner.utils.Constants.VALIDATION_MIN_NAME_LENGTH
import com.tracker.runner.utils.Constants.VALIDATION_MIN_WEIGHT_VALUE
import com.tracker.runner.utils.Constants.VALIDATION_NAME_CONTAINS_FORBIDDEN_CHARACTERS
import com.tracker.runner.utils.Constants.VALIDATION_NAME_GREATER_THAN_MAX_LENGTH
import com.tracker.runner.utils.Constants.VALIDATION_NAME_IS_EMPTY
import com.tracker.runner.utils.Constants.VALIDATION_NAME_LESS_THAN_MIN_LENGTH
import com.tracker.runner.utils.Constants.VALIDATION_NAME_STARTS_WITH_A_DIGIT
import com.tracker.runner.utils.Constants.VALIDATION_NAME_STARTS_WITH_A_LOWER_CASE
import com.tracker.runner.utils.Constants.VALIDATION_NAME_STARTS_WITH_WHITE_SPACE
import com.tracker.runner.utils.Constants.VALIDATION_WEIGHT_GREATER_THAN_MAX_WEIGHT
import com.tracker.runner.utils.Constants.VALIDATION_WEIGHT_IS_EMPTY
import com.tracker.runner.utils.Constants.VALIDATION_WEIGHT_LESS_THAN_MIN_WEIGHT
import com.tracker.runner.utils.Constants.VALIDATION_WEIGHT_WRONG_FORMAT

fun checkValidationName(name: String): ValidationStatus {
    return if (name.isEmpty()) ValidationStatus.Error(VALIDATION_NAME_IS_EMPTY)
    else if (name.length < VALIDATION_MIN_NAME_LENGTH) ValidationStatus.Error(VALIDATION_NAME_LESS_THAN_MIN_LENGTH)
    else if (name.length > VALIDATION_MAX_NAME_LENGTH) ValidationStatus.Error(VALIDATION_NAME_GREATER_THAN_MAX_LENGTH)
    else if (name.matches(Regex("/[0-9!@\$%^&*(),?\":{}|<>`~]/g"))) ValidationStatus.Error(VALIDATION_NAME_CONTAINS_FORBIDDEN_CHARACTERS)
    else if (name.first().isWhitespace()) ValidationStatus.Error(VALIDATION_NAME_STARTS_WITH_WHITE_SPACE)
    else if (name.first().isDigit()) ValidationStatus.Error(VALIDATION_NAME_STARTS_WITH_A_DIGIT)
    else if (name.first().isLowerCase()) ValidationStatus.Error(VALIDATION_NAME_STARTS_WITH_A_LOWER_CASE)
    else ValidationStatus.Success
}

fun checkValidationWeight(weight: String): ValidationStatus {
    return if (weight.isEmpty()) ValidationStatus.Error(VALIDATION_WEIGHT_IS_EMPTY)
    else if (weight.toFloat() < VALIDATION_MIN_WEIGHT_VALUE) ValidationStatus.Error(VALIDATION_WEIGHT_LESS_THAN_MIN_WEIGHT)
    else if (weight.toFloat() > VALIDATION_MAX_WEIGHT_VALUE) ValidationStatus.Error(VALIDATION_WEIGHT_GREATER_THAN_MAX_WEIGHT)
    else if (weight.first() == '.') ValidationStatus.Error(VALIDATION_WEIGHT_WRONG_FORMAT)
    else ValidationStatus.Success
}