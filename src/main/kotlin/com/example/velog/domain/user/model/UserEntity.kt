package com.example.velog.domain.user.model

import com.example.velog.domain.follow.model.FollowEntity
import com.example.velog.domain.user.dto.UserResponseDto
import com.example.velog.domain.user.dto.UserSignUpDto
import com.example.velog.domain.user.dto.UserUpdateDto
import jakarta.persistence.*
import org.springframework.security.crypto.password.PasswordEncoder

@Entity
@Table(name = "app_user")
class UserEntity private constructor(
    @Column(name = "username")
    var userName: String,

    @Column(name = "email")
    var email: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    val role: Role
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long? = null


    // 팔로우
    @OneToMany(mappedBy = "toUser" , fetch = FetchType.LAZY)
    private val followers: List<FollowEntity>? = null

    @OneToMany(mappedBy = "fromUser" , fetch = FetchType.LAZY)
    private val following: List<FollowEntity>? = null




    fun updateUserprofile(userUpdateDto: UserUpdateDto) {
        this.userName = userUpdateDto.userName
        this.email = userUpdateDto.userEmail
    }

    companion object {
        fun toUserEntity(
            userSignUpDto: UserSignUpDto,
            encoder: PasswordEncoder
        ): UserEntity {
            return UserEntity(
                userName = userSignUpDto.userName,
                email = userSignUpDto.userEmail,
                password = encoder.encode(userSignUpDto.password),
                role = Role.MEMBER
            )
        }

        fun toResponse(
            userEntity: UserEntity,
            followEntity: FollowEntity
        ): UserResponseDto {
            return UserResponseDto(
                userId = userEntity.userId!!,
                userName = userEntity.userName,
                userEmail = userEntity.email,
                followState = followEntity.followState,
                followCount = followEntity.followCount,

            )
        }
    }

}


