package com.example.velog.domain.user.dto

data class UserUpdateDto(
    val userId : Long,
    val userName:String,
    val userEmail:String,
    val password:String
)
