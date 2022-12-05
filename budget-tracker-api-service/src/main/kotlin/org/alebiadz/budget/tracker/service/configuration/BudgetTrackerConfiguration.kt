package org.alebiadz.budget.tracker.service.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "budget.tracker")
data class BudgetTrackerConfiguration(
    val jwt: JwtConfiguration
) {

    data class JwtConfiguration(
        val accessTokenSecret: String,
        val refreshTokenSecret: String
    )
}
