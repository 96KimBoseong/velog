package com.example.velog.domain.user.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "users", description = "사용자 API")
@RestController
class UserController {

    @Operation(summary = "회원가입", description = "회원가입.")
    @PostMapping("/signup")
    fun signUp(){
        TODO()
    }

    @Operation(summary = "프로필 수정", description = "사용자의 프로필을 수정합니다.")
    @PutMapping("/users/{userId}/profile")
    fun updateUserProfile(){
        TODO()
    }
}