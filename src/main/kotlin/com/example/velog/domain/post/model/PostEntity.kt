package com.example.velog.domain.post.model

import com.example.velog.domain.comment.model.CommentEntity
import com.example.velog.domain.post.dto.CreatePostRequestDto
import com.example.velog.domain.post.dto.PostDetailResponseDto
import com.example.velog.domain.post.dto.PostResponseDto
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

//Domain Model: Domain Service, Entity, VO(Value Object)를 포함하는 개념

@EntityListeners(AuditingEntityListener::class) //AuditingEntityListener 기능 사용하기 위해서 추가
@Entity //Entity annotation, 객체를 entity로 사용하기 위해서 사용
@Table(name = "post") //매핑할 테이블 이름을 정의
class PostEntity private constructor( //데이터베이스에서 데이터를 가져올 때 사용하는 클래스
    @Column(name = "title") //매핑할 테이블의 컬럼을 정의
    var title: String, //제목은 수정 가능, null 허용 X

    @Column(name = "content") //매핑할 테이블의 컬럼을 정의
    var content: String, //내용은 수정 가능, null 허용 X

    @Column(name = "create_name") //매핑할 테이블의 컬럼을 정의
    var createName: String, //생성한 사람 이름은 수정 불가능, null 허용 X

    @Column(name = "update_name") //매핑할 테이블의 컬럼을 정의
    var updateName: String, //변경한 사람 이름은 수정 가능, null 허용 X

    @OneToMany(mappedBy = "postId", fetch = FetchType.LAZY, cascade=[CascadeType.ALL], orphanRemoval = true)
    var comments: MutableList<CommentEntity> = mutableListOf()
) {
    @Id //PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //데이터베이스에서 ID를 자동으로 생성
    var postId: Long? = null //id는 데이터베이스에서 생성하는 거라서 수정 가능, null 허용

    @CreatedDate
    @Column(name = "create_at") //매핑할 테이블의 컬럼을 정의
    var createAt: LocalDateTime? = null //생성한 시간은 데이터베이스에서 생성하는 거라서 수정 가능, null 허용

    @LastModifiedDate
    @Column(name = "update_at") //매핑할 테이블의 컬럼을 정의
    var updateAt: LocalDateTime? = null //수정한 시간은 데이터베이스에서 생성하는 거라서 수정 가능, null 허용

    @Column(name = "views") //매핑할 테이블의 컬럼을 정의
    var views: Int = 0 //조회수는 수정 가능, null 허용 X, 기본값은 0


    companion object {
        fun toEntity( //Request를 PostEntity로 변환하는 메소드
            requestDto: CreatePostRequestDto
        ): PostEntity {
            return PostEntity(
                title = requestDto.title,
                content = requestDto.content,
                createName = requestDto.createName,
                updateName = requestDto.createName
            )
        }

        fun toResponse( //PostEntity를 Request로 변환하는 메소드
            postEntity: PostEntity
        ): PostResponseDto {
            return PostResponseDto(
                postId = postEntity.postId!!,
                title = postEntity.title,
                content = postEntity.content,
                createAt = postEntity.createAt!!,
                updateAt = postEntity.updateAt!!,
                createName = postEntity.createName,
                updateName = postEntity.updateName,
                views = postEntity.views
            )
        }

        fun toResponseWithComments( //PostEntity를 Request로 변환하는 메소드
            postEntity: PostEntity
        ): PostDetailResponseDto {
            return PostDetailResponseDto(
                postId = postEntity.postId!!,
                title = postEntity.title,
                content = postEntity.content,
                createAt = postEntity.createAt!!,
                updateAt = postEntity.updateAt!!,
                createName = postEntity.createName,
                updateName = postEntity.updateName,
                views = postEntity.views,
                comments = postEntity.comments
            )
        }
    }
}