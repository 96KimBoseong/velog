package com.example.velog.domain.post.dto

/*
* DTO: Data Transfer Object
* 각 Layer 사이의 데이터를 전달하는데 사용되는 객체
* 요청(Request)과 응답(Response) 또한 DTO로 표현 가능
* 작성글 생성 요청(request)을 전달하는 클래스
*/
data class PostCreateRequestDto(
    val title: String, //작성할 게시글 제목
    val content: String //작성할 게시글 내용
)
