package com.example.velog.domain.post.dto

import io.swagger.v3.oas.annotations.media.Schema

/*
* DTO: Data Transfer Object
* 각 Layer 사이의 데이터를 전달하는데 사용되는 객체
* 요청(Request)과 응답(Response) 또한 DTO로 표현 가능
* 작성글 수정 요청(request)을 전달하는 클래스
*/
@Schema(description = "게시글을 수정할 때 입력한 정보를 전달하는 객체")
data class UpdatePostRequestDto(
    @Schema(description = "게시글을 수정한 사람 이름", example = "김성민")
    val updateName: String,

    @Schema(description = "수정한 제목", example = "수정한 제목")
    val title: String, //수정한 게시글 제목

    @Schema(description = "수정한 내용", example = "수정한 내용")
    val content: String //수정한 게시글 내용
)
