package com.example.velog.domain.user.model

import com.example.velog.domain.user.dto.UserResponseDto
import com.example.velog.domain.user.dto.UserSignUpDto
import jakarta.persistence.*

@Table(name = "app_user")
class UserEntity(

    @Column(name = "username")
    val userName:String,

    @Column(name = "email")
    val email:String,

    @Column(name="password")
    val password:String,

//    @Column(name = "role")
//    val role:String,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null
    //db가 아이디 만들어주는데 null일수도 있다 ?
    //response만들 때 위에 적으면 id를 입력해주는 코드를 작성해야함 ?

}

fun UserEntity.toResponse():UserResponseDto{
//확장함수
    return UserResponseDto(
        userId = id!!,
        userName = userName,
        userEmail = email
    )
}
fun UserEntity.createEntity(userSignUpDto: UserSignUpDto):UserEntity{
    return  UserEntity(
        userName = userSignUpDto.userName
    )
}