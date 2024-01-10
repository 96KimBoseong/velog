package com.example.velog.domain.comment.model
import com.example.velog.domain.post.model.PostEntity
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
    val updateName: String,

    @Column
    val postId: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @CreatedDate
    @Column(name = "create_at")
    var createAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "update_at")
    var updateAt: LocalDateTime? = null

    fun changeContent(content: String) {
        this.content = content
    }


}