package com.example.velog.domain.post.service

import com.example.velog.domain.post.repository.PostRepository
import com.example.velog.domain.post.dto.CreatePostRequestDto
import com.example.velog.domain.post.dto.GetAllPostRequestDto
import com.example.velog.domain.post.dto.PostResponseDto
import com.example.velog.domain.post.dto.UpdatePostRequestDto
import com.example.velog.domain.post.model.PostEntity
import com.example.velog.domain.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
): PostService {

    @Transactional
    //게시글을 생성하는 메소드
    override fun createPost(requestDto: CreatePostRequestDto): PostResponseDto {
        val postEntity = PostEntity.toEntity(requestDto)
        return PostEntity.toResponse(postRepository.save(postEntity))
    }

    //모든 게시글 목록을 가져오는 메소드
    override fun getPostList(
        requestDto: GetAllPostRequestDto
    ): List<PostResponseDto> {
        val entityList = if(requestDto.sortBy == "views") postRepository.findAllByOrderByViewsDesc()
        else postRepository.findAllByOrderByCreateAtDesc()

        return entityList.map{PostEntity.toResponse((it))}
    }


    //id에 해당하는 게시글을 가져하는 메소드
    override fun getPost(postId: Long): PostResponseDto {
        val postEntity = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("post", postId)
        postEntity.views++
        return PostEntity.toResponse(postRepository.save(postEntity))
    }

    //id에 해당하는 게시글을 수정하는 메소드
    @Transactional
    override fun updatePost(
        postId: Long,
        requestDto: UpdatePostRequestDto
    ): PostResponseDto {
        val postEntity = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("post", postId)
        postEntity.title = requestDto.title
        postEntity.content = requestDto.content
        postEntity.updateName = requestDto.name

        return PostEntity.toResponse(postRepository.save(postEntity))
    }

    //id에 해당하는 게시글을 삭제하는 메소드
    @Transactional
    override fun deletePost(postId: Long) {
        val postEntity = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("post", postId)
        postRepository.delete(postEntity)
    }
}