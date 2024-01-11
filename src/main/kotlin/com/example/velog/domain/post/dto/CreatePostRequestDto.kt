package com.example.velog.domain.post.dto

import io.swagger.v3.oas.annotations.media.Schema

/*
* DTO: Data Transfer Object
* 각 Layer 사이의 데이터를 전달하는데 사용되는 객체
* 요청(Request)과 응답(Response) 또한 DTO로 표현 가능
* 작성글 생성 요청(request)을 전달하는 클래스
*/

@Schema(description = "게시글을 작성할 때 입력한 정보를 전달하는 객체")
data class CreatePostRequestDto(
    @Schema(description = "게시글을 작성한 사람 이름", example = "황승현")
    val createName: String, //사람 이름

    @Schema(description = "게시글 제목", example = "제목")
    val title: String, //작성할 게시글 제목

    @Schema(description = "게시글 내용", example = "내용")
    val content: String //작성할 게시글 내용
)
