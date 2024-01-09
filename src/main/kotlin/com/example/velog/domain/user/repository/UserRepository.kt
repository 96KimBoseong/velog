package com.example.velog.domain.user.repository

import com.example.velog.domain.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository:JpaRepository<UserEntity,Long> {
    fun existsByEmail(email:String):Boolean
    //이메일이 있냐?ㅇㅇ 불리언값 으로 반환
   // fun findAllByEmail(email: String):List 예시예시

}
//repository는 entity를 이용하여 db에 있는 데이터에 접근한다  sql문을 사용해서 ㅋㅋ