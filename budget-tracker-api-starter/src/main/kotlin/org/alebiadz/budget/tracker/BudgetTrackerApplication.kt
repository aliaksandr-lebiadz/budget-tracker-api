package org.alebiadz.budget.tracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class BudgetTrackerApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<BudgetTrackerApplication>(*args)
        }
    }
}