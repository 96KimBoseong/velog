package com.example.velog.domain.recent.service

import com.example.velog.domain.post.dto.PostResponseDto

interface RecentService {

    fun getRecentList(): List<PostResponseDto>
}