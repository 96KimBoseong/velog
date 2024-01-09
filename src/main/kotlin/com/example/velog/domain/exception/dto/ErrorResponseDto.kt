package com.teamsparta.mytodolist.domain.exception.dto

//DTO: 각 Layer 사이의 데이터를 전달하는데 사용
//응답(Request)과 요청(Response) 또한 DTO로 표현 가능
//하위 layer에서 발생한 에러에 대한 응답(response)을 전달하는 클래스
data class ErrorResponseDto(val message: String?)
