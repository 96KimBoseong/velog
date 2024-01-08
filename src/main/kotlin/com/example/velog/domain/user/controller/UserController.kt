package com.example.velog.domain.user.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @PostMapping("/signup")
    fun signUp(){
        TODO()
    }
    @PutMapping("/users/{userId}/profile")
    fun updateUserProfile(){
        TODO()
    }
}