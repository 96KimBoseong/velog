package com.example.velog.domain.post.dto

import com.example.velog.domain.comment.model.CommentEntity
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "응답을 전달하는 객체")
data class PostDetailResponseDto(
    val postId: Long, //게시글 번호, DB에서 생성됨
    val title: String, //게시글 제목
    val content: String, //게시글 내용
    val createAt: LocalDateTime, //작성 시간
    val updateAt: LocalDateTime, //수정 시간
    val createName: String,
    val updateName: String,
    val views: Int,
    val comments: MutableList<CommentEntity>
)
