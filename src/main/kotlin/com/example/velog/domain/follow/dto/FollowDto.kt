package com.example.velog.domain.follow.dto

import com.example.velog.domain.comment.model.CommentEntity
import com.example.velog.domain.follow.model.FollowEntity
import java.time.LocalDateTime

data class FollowDto(
    val followId: Long,
    val fromUser: String,
    val toUser: String,
)