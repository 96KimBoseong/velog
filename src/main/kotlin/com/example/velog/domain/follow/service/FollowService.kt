package com.example.velog.domain.follow.service

import com.example.velog.domain.comment.dto.CommentDto
import com.example.velog.domain.comment.dto.CreatCommentArguments
import com.example.velog.domain.comment.dto.UpdateCommentArguments

interface FollowService {

    fun createComment(creatCommentArguments: CreatCommentArguments, postId: Long): CommentDto
    fun findByCommentId(commentId: Long): CommentDto
    fun findAllCommentList(postId: Long): List<CommentDto>

    fun updateComment(updateCommentArguments: UpdateCommentArguments, postId: Long, commentId: Long): CommentDto

    fun deleteComment(postId: Long, commentId: Long)


}