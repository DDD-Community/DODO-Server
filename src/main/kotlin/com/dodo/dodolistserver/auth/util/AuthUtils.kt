package com.dodo.dodolistserver.auth.util

import lombok.AccessLevel
import lombok.NoArgsConstructor
import javax.servlet.http.HttpServletRequest

@NoArgsConstructor(access = AccessLevel.PRIVATE)
object AuthUtils {
    private const val AUTH_TOKEN_HEADER = "Authorization"

    fun extractToken(request: HttpServletRequest): String {
        return request.getHeader(AUTH_TOKEN_HEADER)?: throw AuthHeaderOmittedException()
    }
}
