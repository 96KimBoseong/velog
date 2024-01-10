package com.example.velog.domain.comment.dto

import com.example.velog.domain.comment.model.CommentEntity
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import java.time.LocalDateTime

data class CommentDto(
    val id: Long?,
    val content: String,
    val createAt: LocalDateTime?,
    val updateAt: LocalDateTime?,
    val updateName: String,
    val createName: String,
    val postId: Long?
) {
    companion object {
        fun from(comment: CommentEntity): CommentDto {
            return CommentDto(
                id = comment.commentId,
                content = comment.content,
                createAt = comment.createAt,
                updateName = comment.updateName,
                updateAt = comment.updateAt,
                createName = comment.createName,
                postId = comment.postId ?: throw Exception("target post not found")
            )
        }
    }
}