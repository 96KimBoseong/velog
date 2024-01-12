package com.example.velog.domain.follow.controller

import com.example.velog.domain.comment.dto.CommentDto
import com.example.velog.domain.comment.dto.CreatCommentArguments
import com.example.velog.domain.follow.service.FollowService
import com.example.velog.domain.user.model.UserEntity
import com.example.velog.domain.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@Tag(name = "comments", description = "댓글 API")
@RequestMapping("/posts/{postId}/comments")
@RestController
class FollowControllerController(
    private val followService: FollowService
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

    private val userService: UserService? = null
    private val followService: FollowService? = null

    /**
     * 친구 맺기
     */
    @PostMapping("/users/follow/{friendName}")
    fun follow(
        authentication: Authentication,
        @PathVariable("friendName") friendName: String?
    ): ResponseEntity<*> {
        val from_user: UserEntity = userService.findUser(authentication.getName())
        val to_user: UserEntity = userService.findUser(friendName)
        followService.follow(from_user, to_user)
        return ResponseEntity.ok().build<Any>()
    }

    /**
     * 팔로잉 조회
     */
    @GetMapping("/users/{userName}/following")
    fun getFollowingList(
        @PathVariable("userName") userName: String?,
        auth: Authentication
    ): ResponseEntity<List<FollowDTO>> {
        val fromUser: UserEntity = userService.findUser(userName)
        val requestUser: UserEntity = userService.findUser(auth.getName())
        return ResponseEntity.ok().body(followService.followingList(from_user, requestUser))
    }

    /**
     * 팔로워 조회
     */
    @GetMapping("/users/{userName}/follower")
    fun getFollowerList(
        @PathVariable("userName") userName: String?,
        auth: Authentication
    ): ResponseEntity<List<FollowDTO>> {
        val toUser: UserEntity = userService.findUser(userName)
        val requestUser: UserEntity = userService.findUser(auth.getName())
        return ResponseEntity.ok().body(followService.followerList(to_user, requestUser))
    }

    /**
     * 친구 끊기
     */
    @DeleteMapping("/users/follow/{friendName}")
    fun deleteFollow(authentication: Authentication
    ): ResponseEntity<String> {
        return ResponseEntity.ok().body(followService.cancelFollow(userService.findUser(authentication.getName())))
    }


}


