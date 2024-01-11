package com.example.velog.domain.comment.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "댓글을 수정할 때 입력한 정보를 전달하는 객체")
data class UpdateCommentArguments(
    @Schema(description = "수정한 댓글 내용", example = "수정한 댓글 내용")
    val content: String,
    @Schema(description = "수정한 사람 이름", example = "김보성")
    val updateName: String
)