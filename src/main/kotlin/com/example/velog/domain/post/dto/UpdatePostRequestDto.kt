package com.example.velog.domain.post.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

@Schema(description = "게시글을 수정할 때 입력한 정보를 전달하는 객체")
data class UpdatePostRequestDto(
    @field:NotBlank(message = "이름을 입력해주세요.")
    @field:Pattern(
        regexp = "^[ㄱ-ㅎ|가-힣]{2,10}$",
        message = "이름은 2~10자리 한글이여야 합니다."
    )
    @Schema(description = "게시글을 수정한 사람 이름", example = "김성민")
    val updateName: String,

    @field:NotBlank(message = "제목을 입력해주세요.")
    @Size(min = 1, max = 200, message = "제목은 1자 이상 200자 이하로 작성해주세요")
    @Schema(description = "수정한 제목", example = "수정한 제목")
    val title: String,

    @field:NotBlank(message = "본문을 입력해주세요.")
    @Size(min = 1, max = 5000, message = "본문은 1자 이상 5000자 이하로 작성해주세요")
    @Schema(description = "수정한 내용", example = "수정한 내용")
    val content: String
)