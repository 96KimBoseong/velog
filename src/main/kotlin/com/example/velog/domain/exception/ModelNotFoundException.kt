package com.example.velog.domain.exception

//DB에서 찾는 id가 없을 때 에러 메시지를 반환하는 클래스
data class ModelNotFoundException(
    private val modelName: String,
    private val id: Long
): RuntimeException("Model $modelName not found with given id: $id")