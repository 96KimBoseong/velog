package com.example.velog.domain.comment.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "comments", description = "댓글 API")
@RequestMapping("/posts/{postId}/comments")
@RestController
class CommentController {

    @Operation(summary = "댓글 작성", description = "postId를 기준으로 댓글을 작성합니다.")
    @PostMapping
    fun createComment(){
        TODO()
    }
    
    @Operation(summary = "댓글 목록 조회", description = "댓글 목록을 가져옵니다.")
    @GetMapping
    fun getCommentList(){
        TODO()
    }


//        @GetMapping("/{postId}")
//        fun getComment(){
//            TODO()
//        }


    @Operation(summary = "댓글 수정", description = "postId, commentId를 기준으로 댓글을 수정합니다.")
    @PutMapping("/{commentId}")
    fun updateComment(){
        TODO()
    }

    @Operation(summary = "댓글 삭제", description = "postId, commentId를 기준으로 댓글을 삭제합니다.")
    @DeleteMapping("/{commentId}")
    fun deletePost(){
        TODO()

        //야호
    }
}