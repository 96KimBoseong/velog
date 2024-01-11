package com.example.velog.domain.user.service

import com.example.velog.domain.exception.ModelNotFoundException
import com.example.velog.domain.user.dto.*
import com.example.velog.domain.user.model.UserEntity
import com.example.velog.domain.user.model.Role
import com.example.velog.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder, //회원가입할 때 비밀번호를 암호화하기 위해서 사용하는 인코더
    private val tokenProvider: TokenProvider, //토큰 생성하는 객체
    private val authenticationManagerBuilder: AuthenticationManagerBuilder
):UserService {
    @Transactional//트랜젝션은 db에서 쓰는 개념 찾아보기 ㅇㅇ
    override fun signUp(userSignUpDto: UserSignUpDto): UserResponseDto {
        //전달인자랑 반환자료형을 인터페이스에 작성후 오버라이드 단축키 사용
        if(userRepository.existsByEmail(userSignUpDto.userEmail)) throw IllegalStateException("가입된 이메일 입니다.")
        //오류를 던져놓는 코드 받은 dto안에 userEmail이 userRepository안에 있는 existsByEmail - 쿼리문 으로 검증 했을때 db안에 있다면
        //에러를 일으키는 IllegalStateException을 사용하여 소괄호안에 메세지를 출력
        return userRepository.save(
            UserEntity(
                userName = userSignUpDto.userName,
                email = userSignUpDto.userEmail,
                password = encoder.encode(userSignUpDto.password), //비밀번호를 암호화해서 저장함
                role = Role.MEMBER
            )
        ).toResponse()
        // signUp 메서드로 반환
        //무엇을? - userRepository 안에 쿼리문인 save는 만든 entity안에 있는 값을 db에 전달해주는것
        // UserEntity() -> userSignUpDto안에 값으로 UserEntity라는 객체를 생성
        //하고 확장함수인 toResponse를 사용하여 ResponseDto로 이것들의 값을 전달
        //컨트롤러 <-> dto <-> 서비스 <-> entity <-> 레포지토리
    }

    override fun login(userLoginDto: UserLoginDto): TokenInfoDto {
        //유저가 입력한 정보를 이용하여 토큰을 생성
        val authenticationToken = UsernamePasswordAuthenticationToken(userLoginDto.email, userLoginDto.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        //CustomUserDetailService가 실행됨
        return tokenProvider.createToken(authentication)
    }
    // signUp 메서드로 반환
    //무엇을? - userRepository 안에 쿼리문인 save는 만든 entity안에 있는 값을 db에 전달해주는것
    // UserEntity() -> userSignUpDto안에 값으로 UserEntity라는 객체를 생성
    //하고 확장함수인 toResponse를 사용하여 ResponseDto로 이것들의 값을 전달
    //컨트롤러 <-> dto <-> 서비스 <-> entity <-> 레포지토리




    @Transactional
    override fun updateUser(
        userId:Long,
        userUpdateDto: UserUpdateDto
    ): UserResponseDto {
      val userEntity = userRepository.findByIdOrNull(userId)?: throw ModelNotFoundException("user",userId)
        userEntity.updateUserprofile(userUpdateDto)
//        userEntity.userName = userUpdateDto.userName
//        userEntity.email = userUpdateDto.userEmail
//        userRepository.save(userEntity)
//        dirty checking jap
        return userEntity.toResponse()

    }
}
// db에서 가져온 값이 담겨있는 entity를 UserResponseDto로 변환하고 컨트롤러로 보내주는 확장함수
//fun UserEntity.createEntity(userSignUpDto: UserSignUpDto): UserEntity {
//    return  UserEntity(
//        userName = userSignUpDto.userName,
//        email = userSignUpDto.userEmail,
//        password = userSignUpDto.password
//
//    )
//}//UserSignUpDto값을 Entity로 변환하고 db로 보내주는 확장함수 필요없음 이미 엔티티로 받아왔기떄문에?
