package com.example.velog.domain.comment.model

import com.example.velog.domain.post.model.PostEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "Comment")
class CommentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    var content: String,

    @Column
    val create_at: LocalDateTime,

    @Column
    val update_at: LocalDateTime,

    @Column
    val create_name: String,

    @Column
    val update_name: String,

    @Column
    val postId: Long,

    val post: PostEntity

    // 게시글과 댓글 연관 작성해야함.
) {
    fun changeContent(content: String) {
        this.content = content
    }
}