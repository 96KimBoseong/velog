package com.example.velog.domain.recent.service

import com.example.velog.domain.post.dto.PostResponseDto
import com.example.velog.domain.post.model.PostEntity
import com.example.velog.domain.recent.repository.RecentRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class RecentServiceImpl(
    private val recentRepository: RecentRepository
):RecentService {

    override fun getRecentList(
        page: Int,
        size: Int
    ): List<PostResponseDto> {
        return recentRepository.findAllByOrderByCreateAtDesc(PageRequest.of(page, size)).map { PostEntity.toResponse(it) }
    }
}