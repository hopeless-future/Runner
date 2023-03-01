package com.tracker.runner.utils

object Constants {

    const val LOCATION_PERMISSIONS_REQUEST_CODE = 1

    const val VALIDATION_MIN_NAME_LENGTH = 2
    const val VALIDATION_MAX_NAME_LENGTH = 18

    const val VALIDATION_NAME_IS_EMPTY = "Name cannot be empty"
    const val VALIDATION_NAME_STARTS_WITH_WHITE_SPACE = "Name cannot starts with white space"
    const val VALIDATION_NAME_STARTS_WITH_A_DIGIT = "Name cannot starts with a digit"
    const val VALIDATION_NAME_STARTS_WITH_A_LOWER_CASE = "Name cannot starts with a lower case"
    const val VALIDATION_NAME_LESS_THAN_MIN_LENGTH = "Name must contains at least 2 characters"
    const val VALIDATION_NAME_GREATER_THAN_MAX_LENGTH = "Name cannot contains more than 20 characters"
    const val VALIDATION_NAME_CONTAINS_FORBIDDEN_CHARACTERS = "Name contains forbidden characters"

    const val VALIDATION_MIN_WEIGHT_VALUE = 50.0F
    const val VALIDATION_MAX_WEIGHT_VALUE = 120.0F

    const val VALIDATION_WEIGHT_IS_EMPTY = "Weight cannot be empty"
    const val VALIDATION_WEIGHT_LESS_THAN_MIN_WEIGHT = "Weight cannot be less than 50kg"
    const val VALIDATION_WEIGHT_GREATER_THAN_MAX_WEIGHT = "Weight cannot be more than 120kg"
    const val VALIDATION_WEIGHT_WRONG_FORMAT = "Wrong format of the weight"

    const val SHARED_PREFERENCES_NAME_KEY = "name"
    const val SHARED_PREFERENCES_WEIGHT_KEY = "weight"
}