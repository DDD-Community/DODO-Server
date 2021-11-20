package com.dodo.dodolistserver.user.dto

import com.dodo.dodolistserver.user.entity.UserType
import lombok.AccessLevel
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
data class NativeAuthRequestDto(
    @NotBlank
    val email: String,

    @NotBlank
    val password: String
)
