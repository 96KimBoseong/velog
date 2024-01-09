package com.example.velog.domain.post.service

import com.example.velog.domain.post.repository.PostRepository
import com.example.velog.domain.post.dto.CreatePostRequestDto
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
        //작성한 제목이 1자 이상, 200자 이하인지 확인
        if(!textValidation(requestDto.title, "title")) throw IllegalStateException("Title must have between 1 and 200 words")

        //작성한 할 일 본문이 1자 이상인지 확인
        if(!textValidation(requestDto.content, "content")) throw IllegalStateException("Description must have 1 words")

        val postEntity = PostEntity.toEntity(requestDto)
        return PostEntity.toResponse(postRepository.save(postEntity))
    }

    //모든 게시글 목록을 가져오는 메소드
<<<<<<< HEAD
    override fun getPostList(
        requestDto: GetAllPostRequestDto
    ): List<PostResponseDto> {
        val entityList =
            when(requestDto.sortBy){
                "hot" -> postRepository.findAllByOrderByViewsDesc()
                "new" -> postRepository.findAllByOrderByCreateAtDesc()
                else -> throw IllegalStateException("error")
            }
        return entityList.map{PostEntity.toResponse((it))}
    }
    // my_feed -> user가 필요
=======
//    override fun getPostList(
//        requestDto: GetAllPostRequestDto
//    ): List<PostResponseDto> {
//        val entityList =
//            when(requestDto.sortBy){
//                "hot" -> postRepository.findAllByOrderByViewsDesc()
//                "new" -> postRepository.findAllByOrderByCreateAtDesc()
//                else -> throw IllegalStateException("Unsupported sorting method")
//            }
//        return entityList.map{PostEntity.toResponse((it))}
//    }

>>>>>>> ff9a70c56c2f8c187cccc470f78d8ba1bb9c292b

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
        postEntity.updateName = requestDto.updateName

        return PostEntity.toResponse(postRepository.save(postEntity))
    }

    //id에 해당하는 게시글을 삭제하는 메소드
    @Transactional
    override fun deletePost(postId: Long) {
        val postEntity = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("post", postId)
        postRepository.delete(postEntity)
    }

    override fun textValidation(text: String, type: String): Boolean{
        return when (type) {
            "title" -> text.length in (1..200) //제목은 1자 이상, 200자 이내
            "content" -> text.isNotEmpty() //본문이 1자 이상일 때
            else -> false
        }
    }
}