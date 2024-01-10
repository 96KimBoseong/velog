package com.example.velog.domain.comment.controller

import com.example.velog.domain.comment.dto.CommentDto
import com.example.velog.domain.comment.dto.CreatCommentArguments
import com.example.velog.domain.comment.dto.UpdateCommentArguments
import com.example.velog.domain.comment.service.CommentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "comments", description = "댓글 API")
@RequestMapping("/posts/{postId}/comments")
@RestController
class CommentController(
    val commentService: CommentService
) {

    @Operation(summary = "댓글 작성", description = "postId를 기준으로 댓글을 작성합니다.")
    @PostMapping
    fun createComment(
        @PathVariable postId: Long,
        @RequestBody creatCommentArguments: CreatCommentArguments
    ): ResponseEntity<CommentDto> {
        val result = commentService.createComment(creatCommentArguments, postId)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(result)
    }

    @Operation(summary = "댓글 단건 조회", description = "댓글을 조회합니다.")
    @GetMapping("/{commentId}")
    fun findComment(@PathVariable postId: Long, @PathVariable commentId: Long): ResponseEntity<CommentDto> {
        val result = commentService.findByCommentId(commentId)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(result)
    }

    @Operation(summary = "댓글 목록 조회", description = "댓글 목록을 가져옵니다.")
    @GetMapping
    fun getCommentList(
        @PathVariable postId: Long
    ): ResponseEntity<List<CommentDto>> {
        val result = commentService.findAllCommentList(postId)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(result)
    }

    @Operation(summary = "댓글 수정", description = "postId, commentId를 기준으로 댓글을 수정합니다.")
    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable postId: Long, @PathVariable commentId: Long,
        @RequestBody updateCommentArguments: UpdateCommentArguments
    ): ResponseEntity<CommentDto> {
        val arguments = UpdateCommentArguments(
            content = updateCommentArguments.content,
            updateName = updateCommentArguments.updateName
        )
        val comment = commentService.updateComment(arguments, postId, commentId)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(comment)
    }

    @Operation(summary = "댓글 삭제", description = "postId, commentId를 기준으로 댓글을 삭제합니다.")
    @DeleteMapping("/{commentId}")
    fun deletePost(
        @PathVariable postId: Long, @PathVariable commentId: Long,
    ): ResponseEntity<Unit> {
        commentService.deleteComment(postId, commentId)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(null)
    }
}