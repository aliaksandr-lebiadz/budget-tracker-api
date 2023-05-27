package org.alebiadz.budget.tracker.commons.exception

class ObjectNullException(property: String) : BudgetTrackerException("Required property [$property] missed")