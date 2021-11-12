package com.dodo.dodolistserver.common.dto

import com.fasterxml.jackson.annotation.JsonInclude
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import java.time.LocalDateTime
import java.util.*


@Getter
class ResponseDto<T> private constructor(
    val status: Int,
    val message: String?,
    val data: T?,
    val timestamp: LocalDateTime = LocalDateTime.now()) {

    companion object {
        fun <T> of(httpStatus: HttpStatus?, message: String?): ResponseDto<T> {
            val status: Int = Optional.ofNullable(httpStatus)
                .orElse(HttpStatus.OK)
                .value()
            return ResponseDto(status, message, null)
        }

        fun <T> of(httpStatus: HttpStatus?, message: String?, data: T): ResponseDto<T> {
            val status: Int = Optional.ofNullable(httpStatus)
                .orElse(HttpStatus.OK)
                .value()
            return ResponseDto(status, message, data)
        }
    }

}