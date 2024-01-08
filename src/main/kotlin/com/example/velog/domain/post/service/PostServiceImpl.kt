package com.example.velog.domain.post.service

import com.example.velog.domain.post.repository.PostRepository
import com.example.velog.domain.post.dto.PostCreateRequestDto
import com.example.velog.domain.post.dto.PostResponseDto
import com.example.velog.domain.post.dto.PostUpdateRequestDto
import com.example.velog.domain.post.model.PostEntity
import com.teamsparta.mytodolist.domain.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
): PostService {

    //게시글을 생성하는 메소드
    override fun createPost(requestDto: PostCreateRequestDto): PostResponseDto {
        val postEntity = PostEntity.toEntity(requestDto)
        return PostEntity.toResponse(postRepository.save(postEntity))
    }

    //모든 게시글 목록을 가져오는 메소드
    override fun getPostList(): List<PostResponseDto> {
        return postRepository.findAll().map{PostEntity.toResponse(it)}
    }

    //id에 해당하는 게시글을 가져하는 메소드
    override fun getPost(postId: Long): PostResponseDto {
        val postEntity = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("post", postId)
        return PostEntity.toResponse(postEntity)
    }

    //id에 해당하는 게시글을 수정하는 메소드
    override fun updatePost(
        postId: Long,
        requestDto: PostUpdateRequestDto
    ): PostResponseDto {
        val postEntity = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("post", postId)
        postEntity.title = requestDto.title
        postEntity.content = requestDto.content

        return PostEntity.toResponse(postRepository.save(postEntity))
    }

    //id에 해당하는 게시글을 삭제하는 메소드
    override fun deletePost(postId: Long) {
        val postEntity = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("post", postId)
        postRepository.delete(postEntity)
    }
}