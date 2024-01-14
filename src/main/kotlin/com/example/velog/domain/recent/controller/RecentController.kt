package com.example.velog.domain.recent.controller

import com.example.velog.domain.post.dto.PostResponseDto
import com.example.velog.domain.recent.service.RecentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "recent", description = "최근에 만들어진 게시글을 보여주는 API")
@RequestMapping("/recent")
@RestController
class RecentController(
    private val resentService: RecentService
) {
    @Operation(summary = "게시글 목록 조회", description = "게시글 목록을 가져옵니다.")
    @GetMapping
    fun getPostList(
        @RequestParam(name = "page", defaultValue = "0")
        page: Int,

        @RequestParam(name = "size", defaultValue = "10")
        size: Int
    ): ResponseEntity<List<PostResponseDto>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(resentService.getRecentList(page, size))
    }
}
//controller패키지안에 여러개 둬도 ㄱㅊ 알아서 찾아감 ㄱㅊㄱㅊ