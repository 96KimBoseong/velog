package com.example.velog.domain.user.controller

import com.example.velog.domain.user.dto.UserResponseDto
import com.example.velog.domain.user.dto.UserSignUpDto
import com.example.velog.domain.user.dto.UserUpdateDto
import com.example.velog.domain.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "users", description = "사용자 API")
@RestController("users")
class UserController(
    private val userService: UserService
) {

    @Operation(summary = "회원가입", description = "회원가입.")
    @PostMapping("/signup")   //signColler
    fun signUp(userSignUpDto: UserSignUpDto):ResponseEntity<UserResponseDto>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signUp(userSignUpDto))
    }


    @Operation(summary = "사용자 정보 수정", description = "프로필 정보 수정")
    @PutMapping("/{userId}")
    fun userUpdate(
        @PathVariable userId:Long,
        @RequestBody userUpdateDto: UserUpdateDto
    ):ResponseEntity<UserResponseDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.updateUser(userId,userUpdateDto))
    }

//1. 사용자가 로그인을 한다. ()
//    - 아이디, 비밀번호 체크
//    - 승인되면 토큰을 발급한다.
//    - 브라우저에 토큰값을 저장한다.
//
//2. 다른 API를 호출한다.
//    - 브라우저에 저장된 토큰값을 함께 보낸다.
//    - 토큰값을 분석한다. 이 토큰이 올바른가? 사용자 정보는??
//    - 그 정보로 API를 호출한다.
//
//
//1. 회원가입할 때
//
//    -> user 가입대기상태
//
//    임의의 토큰을 발행해 URL만들어서
//    www.naver.com/sign"token=123kljklj12erlj1r2lkjkl12rjrk12ljkl
//
//    sign 0--=-------
//    암호화된걸 풀어서
//    유효한가. 사용자가 가입대기상태인가?
//    상태: 가입대기 -> 가입



}