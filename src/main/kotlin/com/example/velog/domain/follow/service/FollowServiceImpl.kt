package com.example.velog.domain.follow.service

import com.example.velog.domain.follow.dto.FollowDto
import com.example.velog.domain.follow.model.FollowEntity
import com.example.velog.domain.follow.repository.FollowRepository
import com.example.velog.domain.user.model.UserEntity
import com.example.velog.domain.user.repository.UserRepository
import org.springframework.stereotype.Service


@Service
class FollowServiceImpl {

    //follow 요청
    fun follow(from_user: UserEntity, to_user: UserEntity): String {
        // 자기 자신 follow 안됨
        if (from_user === to_user) throw FollowException(ErrorCode.INVALID_REQUEST, "자기 자신을 follow할 수 없습니다.")
        // 중복 follow x
        if (FollowRepository.findFollow(from_user, to_user)
                .isPresent()
        ) throw FollowException(ErrorCode.FOLLOW_DUPLICATED, "이미 follow했습니다.")
        val follow: FollowEntity = FollowEntity.builder()
            .toUser(to_user)
            .fromUser(from_user)
            .build()
        FollowRepository.save(follow)
        return "Success"
    }

    //following 리스트
    fun followingList(selectedUser: UserEntity?, requestUser: UserEntity?): List<FollowDto> {
        val list: List<FollowEntity> = FollowRepository.findByFromUser(selectedUser)
        val followList: MutableList<FollowDto> = ArrayList<FollowDto>()
        for (f in list) {
            followList.add(
                UserRepository.findByUserName(f.getToUser().getUserName())
                    .orElseThrow().toFollow(findStatus(f.getToUser(), requestUser))
            )
        }
        return followList
    }

    //follower list
    fun followerList(selectedUser: UserEntity?, requestUser: UserEntity?): List<FollowDto> {
        val list: List<FollowEntity> = FollowRepository.findByToUser(selectedUser)
        val followerList: MutableList<FollowDto> = ArrayList<FollowDto>()
        for (f in list) {
            followerList.add(
                UserRepository.findByUserName(f.getFromUser().getUserName())
                    .orElseThrow().toFollow(findStatus(f.getFromUser(), requestUser))
            )
        }
        return followerList
    }

    //A와 B의 follow관계 찾기
    protected fun findStatus(selectedUser: UserEntity, requestUser: UserEntity): String {
        if (selectedUser.getUserName() === requestUser.getUserName()) return "self"
        if (FollowRepository.findFollow(requestUser, selectedUser).isEmpty()) return "none"

        return "following"
    }

    fun cancelFollow(user: UserEntity?): String {
        FollowRepository.deleteFollowByFromUser(user)
        return "Success"
    }




}