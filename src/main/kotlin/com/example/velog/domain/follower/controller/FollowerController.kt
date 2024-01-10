package com.example.velog.domain.follower.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users/{userId}")
class FollowerController {

    @PostMapping
    fun follow(){}

    @GetMapping("/profile/followers")
    fun getFollowerList(){}


}