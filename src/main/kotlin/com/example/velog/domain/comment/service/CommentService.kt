package com.example.velog.domain.comment.service

import com.example.velog.domain.comment.dto.CommentDto
import com.example.velog.domain.comment.dto.CreatCommentArguments
import com.example.velog.domain.comment.dto.DeleteCommentArguments
import com.example.velog.domain.comment.dto.UpdateCommentArguments

interface CommentService {
    fun createComment(creatCommentArguments: CreatCommentArguments): CommentDto
    fun findByCommentId(commentId: Long): CommentDto
    fun findAllCommentList(): List<CommentDto>

    fun updateComment(updateCommentArguments: UpdateCommentArguments): CommentDto

    fun deleteComment(deleteCommentArguments: DeleteCommentArguments)


}