package com.dodo.dodolistserver.user.dto

import javax.validation.constraints.NotNull

data class UpdatePasswordDto(
    @NotNull
    val userId: Long,
    @NotNull
    val newPassword: String
)
