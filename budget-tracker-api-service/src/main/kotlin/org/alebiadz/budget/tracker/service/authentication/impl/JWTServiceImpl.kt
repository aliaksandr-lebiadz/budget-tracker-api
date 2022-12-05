package org.alebiadz.budget.tracker.service.authentication.impl

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.alebiadz.budget.tracker.service.authentication.JWTService
import org.alebiadz.budget.tracker.service.configuration.BudgetTrackerConfiguration
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit

@Service
class JWTServiceImpl(private val configuration: BudgetTrackerConfiguration) : JWTService {

    companion object {

        private val ACCESS_TOKEN_LIFETIME = Duration.of(10, ChronoUnit.SECONDS)
        private val REFRESH_TOKEN_LIFETIME = Duration.of(30, ChronoUnit.DAYS)
    }

    override fun generateAccessToken(username: String): String {

        return generateToken(username, configuration.jwt.accessTokenSecret, ACCESS_TOKEN_LIFETIME)
    }

    override fun generateRefreshToken(username: String): String {

        return generateToken(username, configuration.jwt.refreshTokenSecret, REFRESH_TOKEN_LIFETIME)
    }

    override fun decodeAccessToken(token: String): String {

        return decodeToken(token, configuration.jwt.accessTokenSecret)
    }

    override fun decodeRefreshToken(token: String): String {

        return decodeToken(token, configuration.jwt.refreshTokenSecret)
    }

    private fun generateToken(username: String, secret: String, duration: Duration): String {

        val algorithm = algorithm(secret)
        return JWT.create()
            .withSubject(username)
            .withExpiresAt(Instant.now().plus(duration))
            .sign(algorithm)
    }

    private fun decodeToken(token: String, secret: String): String {

        val algorithm = algorithm(secret)
        val verifier = JWT.require(algorithm).build()
        val decodedJwt = verifier.verify(token)
        return decodedJwt.subject
    }

    private fun algorithm(secret: String): Algorithm = Algorithm.HMAC256(secret)
}