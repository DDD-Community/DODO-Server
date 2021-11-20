package com.dodo.dodolistserver.user.dto

data class UpdateUserProfileRequestDto(
    val id: Long,
    val name: String,
    val birth: String

)
