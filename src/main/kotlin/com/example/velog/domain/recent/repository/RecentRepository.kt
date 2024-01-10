package com.example.velog.domain.recent.repository

import com.example.velog.domain.post.model.PostEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RecentRepository: JpaRepository<PostEntity, Long> {
    @Query("select p from PostEntity p left join fetch p.comments order by p.createAt desc ")
    fun findAllByOrderByCreateAtDesc(pageable: Pageable): List<PostEntity>
}