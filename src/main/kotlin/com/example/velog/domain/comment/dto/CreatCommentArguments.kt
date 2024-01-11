package com.example.velog.domain.comment.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "댓글을 작성할 때 입력한 정보를 전달하는 객체")
data class CreatCommentArguments(
    @Schema(description = "작성한 댓글 내용", example = "댓글 내용")
    val content: String,

    @Schema(description = "댓글을 작성한 사람 이름", example = "박현석")
    val createName: String
)
