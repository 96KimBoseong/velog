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
        //엔티티로 감싸준다 userResponseDto를 감싸는 박스가 ResponseEntity - 검색해보기;; ㅜㅜ
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signUp(userSignUpDto))
    }
    // 햄버거만들고 햄버거 상태 좋음 표시 해주고 햄버거를 만들어서 넣어줌 여기서 만든다는건 userService.signUP(userSignUpDto) <- 햄벅만들기ㅇㅇ
    //컨트롤과 서비스는 dto로만 소통한다 == 캐셔?와 요리사는 주문서로만 소통한다
    //
//    @Operation(summary = "프로필 수정", description = "사용자의 프로필을 수정합니다.")
//    @PutMapping("/users/{userId}/profile")
//    fun updateUserProfile(){
//
//    }


}