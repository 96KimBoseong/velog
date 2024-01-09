package com.example.velog.domain.post.service

import com.example.velog.domain.post.dto.CreatePostRequestDto
import com.example.velog.domain.post.dto.PostResponseDto
import com.example.velog.domain.post.dto.UpdatePostRequestDto

/*
* Spring의 Layer의 일부
* Service Layer : Web Layer 하위에 존재하는 Layer
* 트랜잭션(Transaction) 경계의 역할
* Application Service : 응답(response)을 WebLayer(그중에서도 controller)에 넘겨주는 역할
*/
interface PostService {
    
    //게시글을 생성하는 메소드
    fun createPost(requestDto: CreatePostRequestDto): PostResponseDto

    //id에 해당하는 게시글을 가져오는 메소드
    fun getPost(postId: Long): PostResponseDto

    //id에 해당하는 게시글을 수정하는 메소드
    fun updatePost(postId: Long, requestDto: UpdatePostRequestDto): PostResponseDto

    //id에 해당하는 게시글을 삭제하는 메소드
    fun deletePost(postId: Long)

    fun textValidation(text: String, type: String): Boolean

}