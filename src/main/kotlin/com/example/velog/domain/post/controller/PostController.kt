package com.example.velog.domain.post.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "posts", description = "게시글 API")
@RequestMapping("/posts")//베이스지정
@RestController
class PostController {

    @Operation(summary = "게시글 작성", description = "게시글을 작성합니다.")
    @PostMapping
    fun createPost(){
        TODO()
    }

    @Operation(summary = "게시글 목록 조회", description = "게시글 목록을 가져옵니다.")
    @GetMapping
    fun getPostList(){
        TODO()
    }


    @Operation(summary = "게시글 조회", description = "postId를 이용하여 게시글을 조회합니다.")
    @GetMapping("/{postId}")
    fun getPost(){
        TODO()
    }


    @Operation(summary = "게시글 수정", description = "postId를 이용하여 게시글을 수정합니다.")
    @PutMapping("/{postId}")
    fun updatePost(){
        TODO()
    }

    @Operation(summary = "게시글 삭제", description = "postId를 이용하여 게시글을 삭제합니다.")
    @DeleteMapping("/{postId}")
    fun deletePost(){
        TODO()
    }



}//end