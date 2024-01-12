package com.example.velog.domain.follow.model

import com.example.velog.domain.follow.dto.FollowDto
import com.example.velog.domain.user.model.UserEntity
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@Entity
abstract class FollowEntity(

) {

    abstract val followCount: Int
    abstract val followState: Boolean


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private val followId: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_fk") // follow테이블의 "user_id"
    private var toUser: UserEntity? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_fk") // follow테이블의 "follow_user_id"
    private var fromUser: UserEntity? = null


    fun Followers(toUser: UserEntity, fromUser: UserEntity) {
        this.toUser = toUser
        this.fromUser = fromUser
    }


}