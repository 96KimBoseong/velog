package com.example.velog.domain.user.dto

data class CertificationCheckRequest(
    val certificationCode: String,
    val userEmail: String
)