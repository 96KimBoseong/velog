package com.example.velog.domain.comment.service

import com.example.velog.domain.comment.dto.CreatCommentArguments
import com.example.velog.domain.comment.dto.CommentDto
import com.example.velog.domain.comment.dto.DeleteCommentArguments
import com.example.velog.domain.comment.dto.UpdateCommentArguments
import com.example.velog.domain.comment.model.CommentEntity
import com.example.velog.domain.comment.repository.CommentRepository
import com.example.velog.domain.post.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
@Service
class CommentServiceImpl(
    val commentRepository: CommentRepository,
    val postRepository: PostRepository
) : CommentService {
    override fun createComment(creatCommentArguments: CreatCommentArguments): CommentDto {
        val targetPost = postRepository.findByIdOrNull(creatCommentArguments.postId)
            ?: throw Exception("target post is not found")
        val commentEntity = CommentEntity(
            content = creatCommentArguments.content,
            create_name = creatCommentArguments.createName,
            postId = targetPost
        )
        val result = commentRepository.save(commentEntity)
        return CommentDto.from(result)
    }

    override fun findByCommentId(commentId: Long): CommentDto {
        val foundComment = commentRepository.findByIdOrNull(commentId)
            ?: throw Exception("target comment is not found")
        return foundComment.let { CommentDto.from(it) }
    }

    override fun findAllCommentList(): List<CommentDto>{
        val foundComments = commentRepository.findAll()
        return foundComments.map { CommentDto.from(it) }
    }

    override fun updateComment(updateCommentArguments: UpdateCommentArguments): CommentDto {
        val foundComment = updateCommentArguments.id?.let {
            commentRepository.findByIdOrNull(it)
        } ?: throw Exception("target comment is not found")

        foundComment.changeContent(updateCommentArguments.content)
        commentRepository.save(foundComment)
        return CommentDto.from(foundComment)
    }//z

    override fun deleteComment(deleteCommentArguments: DeleteCommentArguments) {
        val foundComment = deleteCommentArguments.id?.let {
            commentRepository.findByIdOrNull(it)
        } ?: throw Exception("target reply is not found")
        commentRepository.deleteById(deleteCommentArguments.id)
    }
} //END