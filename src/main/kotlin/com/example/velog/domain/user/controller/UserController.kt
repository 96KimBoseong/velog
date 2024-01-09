package com.example.velog.domain.user.controller

import com.example.velog.domain.user.dto.UserResponseDto
import com.example.velog.domain.user.dto.UserSignUpDto
import com.example.velog.domain.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "users", description = "사용자 API")
@RestController
class UserController(
    private val userService: UserService
    //서비스와 컨트롤러 연결
) {

    @Operation(summary = "회원가입", description = "회원가입.")
    @PostMapping("/signup")
    fun signUp(userSignUpDto: UserSignUpDto):ResponseEntity<UserResponseDto>{
        //사용자가 입력한 정보를 전달인자로 받고 서비스에서 작업한 유저리스폰스디티오를 리스폰스
        //엔티티로 감싸준다
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signUp(userSignUpDto))
    }
//    @Operation(summary = "프로필 수정", description = "사용자의 프로필을 수정합니다.")
//    @PutMapping("/users/{userId}/profile")
//    fun updateUserProfile(){
//        TODO()
//    }


}