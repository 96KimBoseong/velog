package com.example.velog.domain.user.service

import com.example.velog.domain.user.dto.UserResponseDto
import com.example.velog.domain.user.dto.UserSignUpDto
import com.example.velog.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
):UserService {
    override fun signUp(userSignUpDto: UserSignUpDto): UserResponseDto {
        //전달인자랑 반환자료형을 인터페이스에 작성후 오버라이드 단축키 사용
        if(userRepository.existsByEmail(userSignUpDto.userEmail)) throw IllegalStateException("이미 가입된 이메일입니다.")
        val userEntity = userRepository.save()

    }
}