package com.example.velog.domain.follower.dto

data class FollowerResponseDto(
    val followId:Long,
    val userId:Long,
    val followers: ArrayList<String>
)
