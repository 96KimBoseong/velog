package com.example.velog.domain.trend.repository

import com.example.velog.domain.post.model.PostEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TrendRepository : JpaRepository<PostEntity, Long> {
    fun findAllByOrderByViewsDesc(pageable: Pageable): List<PostEntity>
}