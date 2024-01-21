package org.alebiadz.budget.tracker.commons.exception

import kotlin.reflect.KClass

class EnumerationException(
    value: String,
    allowedValues: List<String>,
    type: KClass<*>
) : BudgetTrackerException("Value $value is not allowed for enum ${type.simpleName}. Allowed values: $allowedValues")
