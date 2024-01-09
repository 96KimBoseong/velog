package com.example.velog.domain.post.dto

data class GetAllPostRequestDto(
    val sortByDescend: Boolean //정렬 순서를 내림차순으로 한다면 true, 아니면 false
)
