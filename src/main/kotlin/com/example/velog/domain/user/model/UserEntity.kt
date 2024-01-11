package com.example.velog.domain.user.model

import com.example.velog.domain.user.dto.UserResponseDto
import com.example.velog.domain.user.dto.UserSignUpDto
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class UserEntity(

    @Column(name = "username")
    var userName:String,

    @Column(name = "email")
    var email:String,

    @Column(name="password")
    val password:String,

//    @Column(name = "role")
//    val role:String,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId:Long?=null
    //db가 아이디 만들어주는데 null일수도 있다 ?
    //response만들 때 위에 적으면 id를 입력해주는 코드를 작성해야함 ?
    //GeneratedValue 검색 해보기 db가 자동으로 id를 들어온 순서대로 지정해줌

}

