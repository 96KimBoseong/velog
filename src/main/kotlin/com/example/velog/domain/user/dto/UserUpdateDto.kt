package com.example.velog.domain.user.dto

data class UserUpdateDto(
    //val userId : Long, 컨트롤러에서 입력받기
    val userName:String,
    val userEmail:String,
    val password:String
)
