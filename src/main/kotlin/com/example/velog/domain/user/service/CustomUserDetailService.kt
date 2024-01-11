package com.example.velog.domain.user.service

import com.example.velog.domain.user.model.UserEntity
import com.example.velog.domain.user.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder, //회원가입할 때 비밀번호를 암호화하기 위해서 사용하는 인코더
) : UserDetailsService{
    override fun loadUserByUsername(username: String): UserDetails {
        val userEntity = userRepository.findByEmail(username) ?: throw IllegalStateException("가입되지 않은 아이디입니다.")
        return createUserDetails(userEntity)
    }

    private fun createUserDetails(userEntity: UserEntity): UserDetails{
        val grantedAuthority: MutableList<SimpleGrantedAuthority> = mutableListOf()
        grantedAuthority.add(SimpleGrantedAuthority("ROLE_${userEntity.role}"))
        return User(
            userEntity.email,
            userEntity.password,
            grantedAuthority
        )
    }
}