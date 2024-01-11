package com.example.velog.domain.post.controller

import com.example.velog.domain.post.dto.CreatePostRequestDto
import com.example.velog.domain.post.dto.PostDetailResponseDto
import com.example.velog.domain.post.dto.PostResponseDto
import com.example.velog.domain.post.service.PostService
import com.example.velog.domain.post.dto.UpdatePostRequestDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

/*
* Spring의 Web Layer의 일부
* Spring의 Service 레이어와 접근하기 위해서 사용하는 클래스
* Client의 요청(Request)을 받고, 응답(Response)을 주는 역할
*/

@Tag(name = "posts", description = "게시글 API") //Swagger에서 확인할 수 있도록 설명 추가
@RequestMapping("/posts") //베이스 URI 지정
@RestController //PostController를 Control layer를 담당하는 Bean으로 등록
class PostController(
    private val postService: PostService //생성자를 이용한 의존성 주입(DI)
) {

    /* 게시글을 생성하는 메소드
    * postCreateRequest를 argument로 받음
    * Service Layer로부터 PostResponseDto DTO를 받아서 ResponseEntity로 감싸고 응답*/
    @PreAuthorize("hasRole('MEMBER')") //가입된 사용자만 가능
    @Operation(summary = "게시글 작성", description = "게시글을 작성합니다.") //Swagger에서 확인할 수 있도록 설명 추가
    @PostMapping //Post 메소드 핸들링, /posts에 접근한다.
    fun createPost(
        @RequestBody requestDto: CreatePostRequestDto
    ): ResponseEntity<PostResponseDto>{
        return ResponseEntity
            .status(HttpStatus.CREATED) //생성 성공하면 201 Created 상태 코드 반환
            .body(postService.createPost(requestDto))
    }

    /* 선택한 게시글을 가져오는 메소드
    * postId를 argument로 받음
    * Service Layer로부터 PostResponseDto DTO를 받아서 ResponseEntity로 감싸고 응답*/
    @Operation(summary = "게시글 조회", description = "postId를 이용하여 게시글을 조회합니다.") //Swagger에서 확인할 수 있도록 설명 추가
    @GetMapping("/{postId}") //Get 메소드 핸들링, /posts/{postId}에 접근한다.
    fun getPost(
        @PathVariable postId: Long
    ): ResponseEntity<PostDetailResponseDto>{ //
        return ResponseEntity
            .status(HttpStatus.OK) //조회 성공하면 200 OK 상태 코드 반환
            .body(postService.getPost(postId))
    }

    /* 선택한 게시글을 수정하는 메소드
    * postId를 argument로 받음
    * Service Layer로부터 PostResponseDto DTO를 받아서 ResponseEntity로 감싸고 응답*/
    @PreAuthorize("hasRole('MEMBER')") //가입된 사용자만 가능
    @Operation(summary = "게시글 수정", description = "postId를 이용하여 게시글을 수정합니다.") //Swagger에서 확인할 수 있도록 설명 추가
    @PutMapping("/{postId}") //Post 메소드 핸들링, /posts/{postId}에 접근한다.
    fun updatePost(
        @PathVariable postId: Long,
        @RequestBody requestDto: UpdatePostRequestDto
    ): ResponseEntity<PostResponseDto>{
        return ResponseEntity
            .status(HttpStatus.OK) //조회 성공하면 200 OK 상태 코드 반환
            .body(postService.updatePost(postId, requestDto))
    }

    /* 선택한 게시글을 삭제하는 메소드
    * postId를 argument로 받음
    * Service layer에서 전달된 DTO가 없으므로 비어있는 ResponseEntity를 반환*/
    @PreAuthorize("hasRole('MEMBER')") //가입된 사용자만 가능
    @Operation(summary = "게시글 삭제", description = "postId를 이용하여 게시글을 삭제합니다.") //Swagger에서 확인할 수 있도록 설명 추가
    @DeleteMapping("/{postId}")
    fun deletePost(
        @PathVariable postId: Long
    ): ResponseEntity<Unit>{
        postService.deletePost(postId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT) //조회 성공하면 204 NO_CONTENT 상태 코드 반환
            .build()
    }

}//end