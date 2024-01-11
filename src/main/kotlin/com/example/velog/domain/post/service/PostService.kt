package com.example.velog.domain.post.service

import com.example.velog.domain.post.dto.CreatePostRequestDto
import com.example.velog.domain.post.dto.PostDetailResponseDto
import com.example.velog.domain.post.dto.PostResponseDto
import com.example.velog.domain.post.dto.UpdatePostRequestDto

interface PostService {
    fun createPost(requestDto: CreatePostRequestDto): PostResponseDto
    fun getPost(postId: Long): PostDetailResponseDto
    fun updatePost(postId: Long, requestDto: UpdatePostRequestDto): PostResponseDto
    fun deletePost(postId: Long)
}