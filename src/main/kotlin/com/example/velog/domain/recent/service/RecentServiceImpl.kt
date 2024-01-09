package com.example.velog.domain.recent.service

import com.example.velog.domain.post.dto.PostResponseDto
import com.example.velog.domain.post.model.PostEntity
import com.example.velog.domain.recent.repository.RecentRepository
import org.springframework.stereotype.Service

@Service
class RecentServiceImpl(
    private val recentRepository: RecentRepository
):RecentService {

    override fun getRecentList(): List<PostResponseDto> {
        return recentRepository.findAllByOrderByCreateAtDesc().map { PostEntity.toResponse(it) }
    }
}