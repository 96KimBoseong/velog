package com.example.velog.domain.trend.controller

import com.example.velog.domain.post.dto.PostResponseDto
import com.example.velog.domain.trend.service.TrendService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "trend", description = "조회수가 높은 게시글을 보여주는 API") //Swagger에서 확인할 수 있도록 설명 추가
@RequestMapping("/trend") //베이스 URI 지정
@RestController //PostController를 Control layer를 담당하는 Bean으로 등록
class TrendController(
    private val trendService: TrendService
) {
    //모든 게시글 목록을 조회수가 높은 순으로 가져오는 메소드
    @Operation(summary = "게시글 목록 조회", description = "게시글 목록을 가져옵니다.") //Swagger에서 확인할 수 있도록 설명 추가
    @GetMapping //Get 메소드 핸들링, /trend에 접근한다.
    fun getPostList(
        @RequestParam(name = "page", defaultValue = "0")
        page: Int,

        @RequestParam(name = "size", defaultValue = "10")
        size: Int
    ): ResponseEntity<List<PostResponseDto>> {
        return ResponseEntity
            .status(HttpStatus.OK) //조회 성공하면 200 OK 상태 코드 반환
            .body(trendService.getTrendList(page, size))
    }
}