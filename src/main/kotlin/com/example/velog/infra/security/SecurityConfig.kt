package com.example.velog.infra.security

import com.example.velog.domain.user.service.JwtAuthenticationFilter
import com.example.velog.domain.user.service.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

//https://docs.spring.io/spring-security/reference/servlet/configuration/kotlin.html 참조
//https://m.blog.naver.com/kimnx9006/220638156019 얘도 참조
//https://velog.io/@dhk22/TIL-Day-71-Kotlin-03-Spring-Security-적용하기-Jwt방식-인증 참조

@Configuration
@EnableMethodSecurity
@EnableWebSecurity //기본적인 Web 보안을 활성화하겠다는 어노테이션
class SecurityConfig(
    private val tokenProvider: TokenProvider
){
    private val allowedUrls = arrayOf(
        "/", "/swagger-ui/**", "/v3/**",
        "/signup", "/login", "posts/{postId}",
        "/recent", "/trend"
    )

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .formLogin { it.disable() }
            .httpBasic { it.disable() }

            //https://developer-youn.tistory.com/156 참조
            //https://kchanguk.tistory.com/197 참조
            //사이트간 위조 요청에 대한 예방 기능이지만, 토큰 인증 방식은 서버에 인증 정보를 보관하지 않기 때문에, CSRF 공격에 대해 어느정도 안전하다.
            .csrf { it.disable() }

            .cors { it.disable() }

            .headers { it.frameOptions { options -> options.sameOrigin() } }

            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //인증 정보를 서버에 담지 않고, 토큰에 담는다.
            }

            .authorizeHttpRequests {
//                it.anyRequest().permitAll() //일단 모든 요청을 허용 가능하게 만듬
                it.requestMatchers(*allowedUrls).permitAll() //모든 사람이 사용 가능함
                    .anyRequest().authenticated()
            }
            .addFilterBefore( //앞에 있는 필터가 뒤에 있는 필터보다 먼저 실행
                JwtAuthenticationFilter(tokenProvider),
                UsernamePasswordAuthenticationFilter::class.java
            )
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder() //비밀번호를 암호화하기 위한 인터페이스인 PasswordEncorder의 구현체
}