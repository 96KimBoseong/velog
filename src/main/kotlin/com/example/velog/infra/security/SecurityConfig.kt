package com.example.velog.infra.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

//https://docs.spring.io/spring-security/reference/servlet/configuration/kotlin.html 참조
//https://m.blog.naver.com/kimnx9006/220638156019 얘도 참조
//https://velog.io/@dhk22/TIL-Day-71-Kotlin-03-Spring-Security-적용하기-Jwt방식-인증 참조

@Configuration
@EnableWebSecurity //기본적인 Web 보안을 활성화하겠다는 어노테이션
class SecurityConfig(

){
    private val allowedUrls = arrayOf("/", "/swagger-ui/**", "/v3/**", "/sign-up", "/login") // sign-up, sign-in 추가
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .formLogin{ it.disable() }
            .httpBasic{ it.disable() }

            //https://developer-youn.tistory.com/156 참조
            //https://kchanguk.tistory.com/197 참조
            //사이트간 위조 요청에 대한 예방 기능이지만, 토큰 인증 방식은 서버에 인증 정보를 보관하지 않기 때문에, CSRF 공격에 대해 어느정도 안전하다.
            .csrf{ it.disable() }

            .cors { it.disable() }

            .headers { it.frameOptions { options -> options.sameOrigin() } }

            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }

            .authorizeHttpRequests{
                it.anyRequest().permitAll() //일단 모든 요청을 허용 가능하게 만듬
//                it.requestMatchers(*allowedUrls).permitAll()
//                    .anyRequest().authenticated()
            }

        return http.build()
    }

    @Bean
    fun bCryptPasswordEncoder() = BCryptPasswordEncoder() //비밀번호를 암호화하기 위한 인터페이스인 PasswordEncorder의 구현체
}