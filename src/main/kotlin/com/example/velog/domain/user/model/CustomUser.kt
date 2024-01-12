package com.example.velog.domain.user.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User

class CustomUser(
    val userId: Long,
    val email: String,
    userName: String,
    password: String,
    authorities: Collection<GrantedAuthority>
) : User(userName, password, authorities)