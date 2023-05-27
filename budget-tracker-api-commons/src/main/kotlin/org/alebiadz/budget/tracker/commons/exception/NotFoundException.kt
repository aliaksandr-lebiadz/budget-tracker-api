package org.alebiadz.budget.tracker.commons.exception

import kotlin.reflect.KClass

class NotFoundException(
    id: String,
    type: KClass<*>
) : BudgetTrackerException("${type.simpleName} with identifier $id not found") {

    constructor(id: Long, type: KClass<*>): this(id.toString(), type)
}