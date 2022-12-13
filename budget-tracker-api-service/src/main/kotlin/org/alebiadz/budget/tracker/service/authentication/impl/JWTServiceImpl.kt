package org.alebiadz.budget.tracker.service.authentication.impl

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.alebiadz.budget.tracker.dto.user.DecodedUserInfoDto
import org.alebiadz.budget.tracker.service.authentication.JWTService
import org.alebiadz.budget.tracker.service.configuration.BudgetTrackerConfiguration
import org.alebiadz.budget.tracker.service.meta.UserMeta
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit

@Service
class JWTServiceImpl(private val configuration: BudgetTrackerConfiguration) : JWTService {

    companion object {

        private val ACCESS_TOKEN_LIFETIME = Duration.of(5, ChronoUnit.MINUTES)
        private val REFRESH_TOKEN_LIFETIME = Duration.of(30, ChronoUnit.DAYS)
    }

    override fun generateAccessToken(username: String, admin: Boolean): String {

        return generateToken(username, admin, configuration.jwt.accessTokenSecret, ACCESS_TOKEN_LIFETIME)
    }

    override fun generateRefreshToken(username: String, admin: Boolean): String {

        return generateToken(username, admin, configuration.jwt.refreshTokenSecret, REFRESH_TOKEN_LIFETIME)
    }

    override fun decodeAccessToken(token: String): DecodedUserInfoDto {

        return decodeToken(token, configuration.jwt.accessTokenSecret)
    }

    override fun decodeRefreshToken(token: String): DecodedUserInfoDto {

        return decodeToken(token, configuration.jwt.refreshTokenSecret)
    }

    private fun generateToken(username: String, admin: Boolean, secret: String, duration: Duration): String {

        val algorithm = algorithm(secret)
        return JWT.create()
            .withSubject(username)
            .withExpiresAt(Instant.now().plus(duration))
            .withClaim(UserMeta.ADMIN, admin)
            .sign(algorithm)
    }

    private fun decodeToken(token: String, secret: String): DecodedUserInfoDto {

        val algorithm = algorithm(secret)
        val verifier = JWT.require(algorithm).build()
        val decodedJwt = verifier.verify(token)

        val username = decodedJwt.subject
        val admin = decodedJwt.getClaim(UserMeta.ADMIN).asBoolean()
        return DecodedUserInfoDto(username, admin)
    }

    private fun algorithm(secret: String): Algorithm = Algorithm.HMAC256(secret)
}