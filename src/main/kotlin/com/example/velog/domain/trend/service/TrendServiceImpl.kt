package com.example.velog.domain.trend.service

import com.example.velog.domain.post.dto.PostResponseDto
import com.example.velog.domain.post.model.PostEntity
import com.example.velog.domain.trend.repository.TrendRepository
import org.springframework.stereotype.Service

@Service
class TrendServiceImpl(
    private val trendRepository: TrendRepository
):TrendService {
    override fun getTrendList(): List<PostResponseDto> {
        return trendRepository.findAllByOrderByViewsDesc().map{ PostEntity.toResponse(it)}
    }
}