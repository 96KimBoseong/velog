package com.example.velog.domain.post.dto

import java.time.LocalDateTime

/*
* DTO: Data Transfer Object
* 각 Layer 사이의 데이터를 전달하는데 사용되는 객체
* 요청(Request)과 응답(Response) 또한 DTO로 표현 가능
* 다른 layer로 데이터를 전달할 때(응답할 때) 사용되는 클래스
*/
data class PostResponseDto(
    val postId: Long, //게시글 번호, DB에서 생성됨
    val title: String, //게시글 제목
    val content: String, //게시글 내용
    val createAt: LocalDateTime, //작성 시간
    val updateAt: LocalDateTime, //수정 시간
    val createName: String,
    val updateName: String,
    val views: Int
)
