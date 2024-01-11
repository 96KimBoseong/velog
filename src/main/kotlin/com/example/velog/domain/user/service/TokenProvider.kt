package com.example.velog.domain.user.service

import com.example.velog.domain.user.dto.TokenInfoDto
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@PropertySource("classpath:jwt.yml")
@Service
class TokenProvider(
    @Value("\${secret}")
    private val secretKey: String,

    @Value("\${accessExpiration}")
    private val accessExpiration: Long,

    @Value("\${refreshExpiration}")
    private val refreshExpiration: Long

) {
    private val key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
    fun createToken(authentication: Authentication): TokenInfoDto {
        val authorities: String = authentication
            .authorities
            .joinToString(",", transform = GrantedAuthority::getAuthority)

        val now = Date()
        val accessExpiration = Date(now.time + accessExpiration)
        val refreshExpiration = Date(now.time + refreshExpiration)

        val accessToken: String = Jwts.builder()
            .setSubject(authentication.name)
            .claim("auth", authorities)
            .setIssuedAt(now)
            .setExpiration(accessExpiration)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

        val refreshToken: String = Jwts.builder()
            .setIssuedAt(now)
            .setExpiration(refreshExpiration)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

        return TokenInfoDto("Bearer", accessToken, refreshToken)
    }

    fun getAuthentication(accessToken: String): Authentication {
        //JWT 복호화
        val claims: Claims = getClaims(accessToken)

        val auth = claims["auth"] ?: throw RuntimeException("This is an unauthenticated token")

        val authorities: Collection<GrantedAuthority> = (auth as String)
            .split(",")
            .map { SimpleGrantedAuthority(it) }

        val principal: UserDetails = User(claims.subject, "", authorities)
        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }

    fun validateToken(token: String): Boolean {
        try {
            getClaims(token)
            return true
        } catch (e: Exception) {
            when (e) {
                is SecurityException -> {}
                is MalformedJwtException -> {}
                is ExpiredJwtException -> {}
                is UnsupportedJwtException -> {}
                is IllegalArgumentException -> {}
                else -> {}
            }
        }
        return false
    }

    private fun getClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
    }
}