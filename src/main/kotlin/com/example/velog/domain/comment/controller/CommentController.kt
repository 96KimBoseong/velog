package com.example.velog.domain.comment.controller

import org.springframework.web.bind.annotation.*
@RequestMapping("/posts/{postId}/comments")
@RestController
class CommentController {
        @PostMapping
        fun createComment(){
            TODO()
        }

        @GetMapping
        fun getCommentList(){
            TODO()
        }


//        @GetMapping("/{postId}")
//        fun getComment(){
//            TODO()
//        }

        @PutMapping("/{commentId}")
        fun updateComment(){
            TODO()
        }

        @DeleteMapping("/{commentId}")
        fun deletePost(){
            TODO()
        }



}