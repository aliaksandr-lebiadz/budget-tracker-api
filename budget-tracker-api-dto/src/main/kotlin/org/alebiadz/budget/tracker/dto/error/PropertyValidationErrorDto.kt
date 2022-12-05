package org.alebiadz.budget.tracker.dto.error

data class PropertyValidationErrorDto (
    val property: String,
    val errors: List<String>
)