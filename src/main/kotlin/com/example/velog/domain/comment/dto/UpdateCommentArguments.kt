package com.example.velog.domain.comment.dto

data class UpdateCommentArguments(
    val id: Long?,
    val content: String,
    val updateName: String
)