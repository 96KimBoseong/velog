package com.example.velog.domain.user.controller

import com.example.velog.domain.user.dto.*
import com.example.velog.domain.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "users", description = "사용자 API")
@RestController("users")
class UserController(
    private val userService: UserService
) {
    @Operation(summary = "회원가입", description = "회원가입.")
    @PostMapping("/signup")
    fun signUp(@Valid @RequestBody userSignUpDto: UserSignUpDto): ResponseEntity<UserResponseDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signUp(userSignUpDto))
    }

    @Operation(summary = "사용자 정보 수정", description = "프로필 정보 수정")
    @PutMapping("/{userId}")
    fun userUpdate(
        @PathVariable userId: Long,
        @RequestBody userUpdateDto: UserUpdateDto
    ): ResponseEntity<UserResponseDto> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.updateUser(userId, userUpdateDto))
    }

    @Operation(summary = "로그인", description = "사용자가 입력한 아이디와 비밀번호로 로그인을 시도.")
    @PostMapping("/login")
    fun login(@Valid @RequestBody userLoginDto: UserLoginDto): ResponseEntity<TokenInfoDto> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.login(userLoginDto))
    }

    // 팔로우 기능
    @Operation(summary = "팔로우하기" , description = "관련 Service에 필요한 정보를 넘기며 호출")
    @GetMapping("/user/{pageUserId}")
    fun profile(
        @PathVariable pageUserId: Int,
        model: Model,
        @AuthenticationPrincipal customUserDetails: CustomUserDetails
    ): String? {
        val userProfileDto: UserProfileDto = userService.profile(pageUserId, customUserDetails.getUser().getId())
        model.addAttribute("dto", userProfileDto)

        return "user/profile"
    }



}