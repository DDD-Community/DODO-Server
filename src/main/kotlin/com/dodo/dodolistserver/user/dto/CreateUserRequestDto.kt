package com.dodo.dodolistserver.user.dto

import com.dodo.dodolistserver.user.entity.UserType
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class CreateUserRequestDto(
    @NotNull
    val userType: UserType,

    @NotNull
    val name: String,

    @NotNull
    val email: String,

    val password: String?,

    val birth: String,
)