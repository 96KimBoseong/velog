package com.example.velog.domain.user.service

import com.example.velog.domain.user.dto.*

interface UserService {

    fun signUp(userSignUpDto: UserSignUpDto): UserResponseDto
    //사인업 메서드에 전달인자를 유저사인업디티오로 받고 유저리스폰스디티오를 반환

    fun updateUser(userId:Long,userUpdateDto: UserUpdateDto):UserResponseDto
    //userUpdate 메소드 전달인자를 userId와 userUpdateDto로 받고 USerResponseDto로 반환

    fun login(userLoginDto: UserLoginDto): TokenInfoDto
}