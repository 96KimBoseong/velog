package com.example.velog.domain.user.service

import com.example.velog.domain.user.dto.TokenInfoDto
import com.example.velog.domain.user.dto.UserLoginDto
import com.example.velog.domain.user.dto.UserResponseDto
import com.example.velog.domain.user.dto.UserSignUpDto

interface UserService {

    fun signUp(userSignUpDto: UserSignUpDto): UserResponseDto
    //사인업 메서드에 전달인자를 유저사인업디티오로 받고 유저리스폰스디티오를 반환

    //로그인 메소드
    fun login(userLoginDto: UserLoginDto): TokenInfoDto
}