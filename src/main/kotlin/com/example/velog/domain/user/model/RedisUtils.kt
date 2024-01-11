package com.example.velog.domain.user.model

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class RedisUtils(
    private val redisTemplate: RedisTemplate<String, String>,
) {

    private val durationTime = 1000 * 60 * 5L // 5분


    //인증코드값  조회
    fun getData(userEmail: String, verificationCode: String): String? {
        val valueOperations = redisTemplate.opsForValue()
        return valueOperations[userEmail]
    }

    //인증코드 임시저장
    fun setDataExpire(userEmail: String, verificationCode: String) {
        val valueOperations = redisTemplate.opsForValue()
        valueOperations.set(userEmail, verificationCode, Duration.ofMillis(durationTime))
    }

    //저장된 코드값 삭제
    fun deleteData(userEmail: String) {
        redisTemplate.delete(userEmail)
    }

}