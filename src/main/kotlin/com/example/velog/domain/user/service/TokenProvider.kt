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

//JWT에 관련된 암호화, 복호화, 검증 로직이 이루어지는 클래스
@PropertySource("classpath:jwt.yml") //application.yml말고 jwt.yml에서 데이터 가져오기
@Service
class TokenProvider(
    @Value("\${secret}")
    private val secretKey: String,

    @Value("\${accessExpiration}")
    private val accessExpiration: Long,

    @Value("\${refreshExpiration}")
    private val refreshExpiration: Long

){
    private val key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
    fun createToken(authentication: Authentication): TokenInfoDto { //token을 생성하는 메소드
        //Authentication 클래스에 있는 권한들을 string으로 뽑아냄
        val authorities: String = authentication
            .authorities
            .joinToString(",", transform = GrantedAuthority::getAuthority)

        val now = Date()
        val accessExpiration = Date(now.time + accessExpiration) //1시간 후 만료
        val refreshExpiration = Date(now.time + refreshExpiration) //일주일 후 만료

        //accessToken = 서버의 인증 용도로 사용하는 JWT
        val accessToken: String = Jwts.builder()
            .setSubject(authentication.name) //유저 아이디가 들어감
            .claim("auth", authorities) //사용자 권한 설정(예를 들어 MEMBER)
            .setIssuedAt(now) //accessToken 발급된 시간
            .setExpiration(accessExpiration) //accessToken 만료 시간, timestamp 형태
            .signWith(key, SignatureAlgorithm.HS256) // HS256 알고리즘을 사용하여 secretKey를 이용해 서명
            .compact() //accessToken 생성

        //refreshToken = accessToken을 재발급하기 위한 용도의 JWT
        //유저 정보를 담지 않는다.
        val refreshToken: String = Jwts.builder()
            .setIssuedAt(now) //refreshToken 발급된 시간
            .setExpiration(refreshExpiration) //refreshToken 만료 시간, timestamp 형태
            .signWith(key, SignatureAlgorithm.HS256) // HS256 알고리즘을 사용하여 secretKey를 이용해 서명
            .compact()

        return TokenInfoDto("Bearer", accessToken, refreshToken)
    }

    //token 정보를 추출하는 메소드
    //Access Token에만 유저 정보가 들어있기 때문에 accessToken으로 명시
    fun getAuthentication(accessToken: String): Authentication{
        //JWT 복호화
        val claims: Claims = getClaims(accessToken) // claim = key, value 형태로 표현되는 각각의 정보

        val auth = claims["auth"] ?: throw RuntimeException("This is an unauthenticated token") //토큰이 권한 정보를 가졌는지 확인

        //claim에서 권한 정보 추출
        val authorities: Collection<GrantedAuthority> = (auth as String)
            .split(",")
            .map { SimpleGrantedAuthority(it) }

        // UserDetails 객체를 만들어서 Authentication 리턴
        val principal: UserDetails = User(claims.subject, "", authorities)
        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }

    //Token을 검증하는 메소드
    fun validateToken(token: String): Boolean{
        try {
            getClaims(token) //getClaims에 성공한다면
            return true //true를 반환
        } catch (e: Exception) { //실패한다면
            when (e) {
                is SecurityException -> {} //유효하지 않은(Invalid) JWT
                is MalformedJwtException -> {} //유효하지 않은(Invalid) JWT
                is ExpiredJwtException -> {} //만료된 JWT
                is UnsupportedJwtException -> {} //지원되지 않는 JWT
                is IllegalArgumentException -> {} //JWT Claims String이 비어있다
                else -> {}
            }
        }
        return false //실패한다면 false를 반환
    }

    private fun getClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
    }
}