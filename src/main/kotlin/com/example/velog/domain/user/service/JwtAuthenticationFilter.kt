package com.example.velog.domain.user.service

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.GenericFilterBean
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val tokenProvider: TokenProvider
//) : GenericFilterBean() { //GenericFilterBean을 상속받음
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        //Request Header에서 토큰을 꺼냄
        val token = resolveToken(request)

        if (token != null && tokenProvider.validateToken(token)) { //null이 없다면 정상 토큰으로 본다
            val authentication = tokenProvider.getAuthentication(token) //정상 토큰이면 해당 토큰으로 Authentication을 가져와서
            SecurityContextHolder.getContext().authentication = authentication //SecurityContextHolder에 저장
        }

        filterChain.doFilter(request, response)
    }

//실제 필터링 로직은 doFilter에서 수행
    //jwt의 인증 정보를 현재 실행중인 스레드(Security Context)에 저장
//    override fun doFilter(
//        request: ServletRequest?,
//        response: ServletResponse?,
//        chain: FilterChain?
//    ) {
//        //Request Header에서 토큰을 꺼냄
//        val token = resolveToken(request as HttpServletRequest)
//
//        //validateToken 메소드로 토큰 유효성 검사
//        if (token != null && tokenProvider.validateToken(token)) { //null이 없다면 정상 토큰으로 본다
//            val authentication = tokenProvider.getAuthentication(token) //정상 토큰이면 해당 토큰으로 Authentication을 가져와서
//            SecurityContextHolder.getContext().authentication = authentication //SecurityContextHolder에 저장
//        }
//        chain?.doFilter(request, response)
//    }

    // HttpServletRequest 객체의 Header에서 token을 꺼내는 역할을 수행하는 메소드
    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION)

        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
            bearerToken.substring(7) //Bearer 문자열 뒤에 있는 값만 가져옴
        } else null
    }

}