package com.dodo.dodolistserver.auth.provider

import com.dodo.dodolistserver.common.exception.TokenExpireException
import com.dodo.dodolistserver.common.exception.TokenValidationException
import com.dodo.dodolistserver.user.entity.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import java.util.*


class JWTTokenProvider: TokenProvider {
    @Value("\${jwt.token.secret.key}")
    private val tokenSecretKey: String? = null

    @Value("\${jwt.token.expiration.seconds}")
    private val expireTime: Long = 0

    override fun createToken(user: User): String {
        val claims: Claims = Jwts.claims()
            .setSubject(user.id.toString())
        val now = Date()
        val expireDate = Date(now.time + expireTime)
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expireDate)
            .signWith(SignatureAlgorithm.HS256, tokenSecretKey)
            .compact()
    }

    override fun extractIdByToken(token: String): Long {
        val subjectStr = getClaimFromToken<String?>(token, Claims::getSubject)
            ?: throw TokenValidationException()
        return subjectStr.toLong()
    }

    private fun <T> getClaimFromToken(token: String, func :(Claims) -> T): T {
        val allClaims: Claims = getAllClaims(token)
        return func(allClaims)
    }

    private fun getAllClaims(token: String): Claims {
        return try {
            Jwts.parser()
                .setSigningKey(tokenSecretKey)
                .parseClaimsJws(token)
                .body
        } catch (ignore: IllegalArgumentException) {
            throw TokenValidationException()
        }
    }

    override fun isValidToken(token: String): Boolean {
        val expireDate: Date = getClaimFromToken<Date>(token, Claims::getExpiration)
        if (expireDate.before(Date())) {
            throw TokenExpireException()
        }
        return true
    }
}