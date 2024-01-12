package com.example.velog.domain.post.controller

import com.example.velog.domain.post.dto.CreatePostRequestDto
import com.example.velog.domain.post.dto.PostDetailResponseDto
import com.example.velog.domain.post.dto.PostResponseDto
import com.example.velog.domain.post.service.PostService
import com.example.velog.domain.post.dto.UpdatePostRequestDto
import com.example.velog.domain.user.model.UserEntity
import com.example.velog.domain.user.service.CustomUserDetailService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "posts", description = "게시글 API")
@RequestMapping("/posts")
@RestController
class PostController(
    private val postService: PostService
) {
    @PreAuthorize("hasRole('MEMBER')")
    @Operation(summary = "게시글 작성", description = "게시글을 작성합니다.")
    @PostMapping
    fun createPost(
        @Valid @RequestBody requestDto: CreatePostRequestDto,
    ): ResponseEntity<PostResponseDto> {

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(postService.createPost(requestDto))
    }

    @Operation(summary = "게시글 조회", description = "postId를 이용하여 게시글을 조회합니다.")
    @GetMapping("/{postId}")
    fun getPost(
        @PathVariable postId: Long
    ): ResponseEntity<PostDetailResponseDto> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPost(postId))
    }

    @PreAuthorize("hasRole('MEMBER')")
    @Operation(summary = "게시글 수정", description = "postId를 이용하여 게시글을 수정합니다.")
    @PutMapping("/{postId}")
    fun updatePost(
        @PathVariable postId: Long,
        @Valid @RequestBody requestDto: UpdatePostRequestDto,
    ): ResponseEntity<PostResponseDto> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.updatePost(postId, requestDto))
    }

    @PreAuthorize("hasRole('MEMBER')")
    @Operation(summary = "게시글 삭제", description = "postId를 이용하여 게시글을 삭제합니다.")
    @DeleteMapping("/{postId}")
    fun deletePost(
        @PathVariable postId: Long
    ): ResponseEntity<Unit> {
        postService.deletePost(postId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}