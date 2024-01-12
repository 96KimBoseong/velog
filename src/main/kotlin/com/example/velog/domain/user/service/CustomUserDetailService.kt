package com.example.velog.domain.user.service

import com.example.velog.domain.user.model.CustomUser
import com.example.velog.domain.user.model.UserEntity
import com.example.velog.domain.user.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails { //username = 유저가 입력한 이메일 아이디
        return createUserDetails(
            userRepository.findByEmail(username) ?: throw IllegalStateException("가입되지 않은 아이디입니다.")
        )
    }

    private fun createUserDetails(userEntity: UserEntity): UserDetails {
        val grantedAuthority: MutableList<SimpleGrantedAuthority> = mutableListOf()
        grantedAuthority.add(SimpleGrantedAuthority("ROLE_${userEntity.role}"))
        return CustomUser(
            userId = userEntity.userId!!,
            email = userEntity.email,
            userName = userEntity.userName,
            password = userEntity.password,
            authorities = grantedAuthority
        )
    }
}