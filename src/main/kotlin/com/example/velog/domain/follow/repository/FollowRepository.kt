package com.example.velog.domain.follow.repository

import com.example.velog.domain.follow.model.FollowEntity
import com.example.velog.domain.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*


interface FollowRepository : JpaRepository<FollowEntity, Long?> {
    fun countByToUser(user: UserEntity?): Long? // 팔로워 수 (follower)
    fun countByFromUser(user: UserEntity?): Long? // 팔로우 수 (following)

    fun findAllByFromUser(fromUser: UserEntity): List<FollowEntity?>? // 사용자가 팔로우한 관계를 가져옴
    fun findAllByToUser(toUser: UserEntity): List<FollowEntity?>? // 사용자를 팔로우하는 관계를 가져옴
    fun deleteFollowByFromUser(fromUser: UserEntity?)
    @Query("select f from FollowEntity f where f.fromUser = :from and f.toUser = :to")
    fun findFollow(@Param("from") fromUser: UserEntity?, @Param("to") toUser: UserEntity?): Optional<FollowEntity?>?


}