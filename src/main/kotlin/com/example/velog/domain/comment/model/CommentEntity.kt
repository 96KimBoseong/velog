package com.example.velog.domain.comment.model

import com.example.velog.domain.comment.dto.UpdateCommentArguments
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@Entity
@Table(name = "Comment")
class CommentEntity(

    @Column
    var content: String,

    @Column(name = "create_name")
    val createName: String,

    @Column(name = "update_name")
    var updateName: String,

    @Column
    val postId: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val commentId: Long? = null

    @CreatedDate
    @Column(name = "create_at")
    var createAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "update_at")
    var updateAt: LocalDateTime? = null

    fun changeUpdateComment(updateCommentArguments: UpdateCommentArguments) {
        this.content = updateCommentArguments.content
        this.updateName = updateCommentArguments.updateName
    }

}