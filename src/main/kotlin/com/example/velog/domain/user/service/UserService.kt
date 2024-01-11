package com.example.velog.domain.user.service

import com.example.velog.domain.user.dto.*

interface UserService {
    fun signUp(userSignUpDto: UserSignUpDto): UserResponseDto
    fun updateUser(userId: Long, userUpdateDto: UserUpdateDto): UserResponseDto
    fun login(userLoginDto: UserLoginDto): TokenInfoDto
}