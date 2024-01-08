package com.example.velog.domain.post.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/posts")//베이스지정
@RestController
class PostController {
    @PostMapping
    fun createPost(){
        TODO()
    }

    @GetMapping
    fun getPostList(){
        TODO()
    }


    @GetMapping("/{postId}")
    fun getPost(){
        TODO()
    }

    @PutMapping("/{postId}")
    fun updatePost(){
        TODO()
    }

    @DeleteMapping("/{postId}")
    fun deletePost(){
        TODO()
    }



}//end