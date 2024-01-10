package com.example.velog.domain.recent.repository

import com.example.velog.domain.post.model.PostEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RecentRepository: JpaRepository<PostEntity, Long> {

    fun findAllByOrderByCreateAtDesc(): List<PostEntity>
}