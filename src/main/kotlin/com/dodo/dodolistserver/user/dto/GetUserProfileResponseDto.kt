package com.dodo.dodolistserver.user.dto

import com.dodo.dodolistserver.user.entity.UserType

data class GetUserProfileResponseDto(
    val id : Long,
    val type : UserType,
    val name : String,
    val email : String,
    val birth : String
)
