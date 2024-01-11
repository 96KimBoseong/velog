package com.example.velog.domain.user.dto

/*
* DTO: Data Transfer Object
* 각 Layer 사이의 데이터를 전달하는데 사용되는 객체
* 요청(Request)과 응답(Response) 또한 DTO로 표현 가능
* 로그인시 토큰 정보를 전달하는 클래스
*/
data class TokenInfoDto(
    val grantType: String,
    val accessToken: String,
    val refreshToken: String
)
