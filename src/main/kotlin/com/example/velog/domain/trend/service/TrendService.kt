package com.example.velog.domain.trend.service

import com.example.velog.domain.post.dto.PostResponseDto

interface TrendService {
    fun getTrendList(page: Int, size: Int): List<PostResponseDto>
}