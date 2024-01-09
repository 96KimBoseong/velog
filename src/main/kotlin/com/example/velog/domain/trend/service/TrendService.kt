package com.example.velog.domain.trend.service

import com.example.velog.domain.post.dto.PostResponseDto

/*
* Spring의 Layer의 일부
* Service Layer : Web Layer 하위에 존재하는 Layer
* 트랜잭션(Transaction) 경계의 역할
* Application Service : 응답(response)을 WebLayer(그중에서도 controller)에 넘겨주는 역할
*/
interface TrendService {

    //모든 게시글 목록을 조회수가 높은 순으로 가져오는 메소드
    fun getTrendList(): List<PostResponseDto>
}