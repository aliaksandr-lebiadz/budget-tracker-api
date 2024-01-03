package org.alebiadz.budget.tracker.service.authentication.impl

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.alebiadz.budget.tracker.dto.user.JwtUserDto
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

        private val ACCESS_TOKEN_LIFETIME = Duration.of(10, ChronoUnit.SECONDS)
        private val REFRESH_TOKEN_LIFETIME = Duration.of(30, ChronoUnit.DAYS)
    }

    override fun generateAccessToken(user: JwtUserDto): String {

        return generateToken(user, configuration.jwt.accessTokenSecret, ACCESS_TOKEN_LIFETIME)
    }

    override fun generateRefreshToken(user: JwtUserDto): String {

        return generateToken(user, configuration.jwt.refreshTokenSecret, REFRESH_TOKEN_LIFETIME)
    }

    override fun decodeAccessToken(token: String): JwtUserDto {

        return decodeToken(token, configuration.jwt.accessTokenSecret)
    }

    override fun decodeRefreshToken(token: String): JwtUserDto {

        return decodeToken(token, configuration.jwt.refreshTokenSecret)
    }

    private fun generateToken(user: JwtUserDto, secret: String, duration: Duration): String {

        val algorithm = algorithm(secret)
        return JWT.create()
            .withSubject(user.id.toString())
            .withExpiresAt(Instant.now().plus(duration))
            .withClaim(UserMeta.USERNAME, user.username)
            .withClaim(UserMeta.ADMIN, user.admin)
            .sign(algorithm)
    }

    private fun decodeToken(token: String, secret: String): JwtUserDto {

        val algorithm = algorithm(secret)
        val verifier = JWT.require(algorithm).build()
        val decodedJwt = verifier.verify(token)

        val userId = decodedJwt.subject.toLong()
        val username = decodedJwt.getClaim(UserMeta.USERNAME).asString()
        val admin = decodedJwt.getClaim(UserMeta.ADMIN).asBoolean()
        return JwtUserDto(userId, username, admin)
    }

    private fun algorithm(secret: String): Algorithm = Algorithm.HMAC256(secret)
}