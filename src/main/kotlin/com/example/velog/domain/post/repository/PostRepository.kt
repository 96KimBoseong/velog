package com.example.velog.domain.post.repository

import com.example.velog.domain.post.model.PostEntity
import org.springframework.data.jpa.repository.JpaRepository

/*
* Spring Layer의 일부
* Repository layer: 가장 하위에 존재하는 Layer
* 데이터베이스와 통신하는 역할 담당 (값 수정, 값 추가, 삭제, 값 가져오기 등)
* Java 및 kotlin 객체를 사용해서 DB에 대해 CRUD를 쉽게 할 수 있도록 하는 JPA를 사용해서 DB와 통신
*/
interface PostRepository: JpaRepository<PostEntity, Long> {

}