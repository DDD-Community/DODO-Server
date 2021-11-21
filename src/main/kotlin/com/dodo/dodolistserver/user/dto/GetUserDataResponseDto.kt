package com.dodo.dodolistserver.user.dto

data class GetUserDataResponseDto(
    val id : Long,
    val name : String,
    val projects : List<UserProjectDataResponseDto>
)
