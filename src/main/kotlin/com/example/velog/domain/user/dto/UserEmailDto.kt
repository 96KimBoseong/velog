package com.example.velog.domain.user.dto

data class UserEmailDto(
    val userEmail: String,
    val verificationCode: String
)