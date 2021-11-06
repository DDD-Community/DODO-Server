package com.dodo.dodolistserver.user.dto

import com.dodo.dodolistserver.user.entity.UserType

data class AuthResultDto(
    val userId: Long,
    val userType: UserType,
    val token: String
)