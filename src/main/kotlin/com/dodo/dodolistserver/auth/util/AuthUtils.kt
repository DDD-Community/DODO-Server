package com.dodo.dodolistserver.auth.util

import com.dodo.dodolistserver.common.exception.auth.AuthHeaderOmittedException
import lombok.AccessLevel
import lombok.NoArgsConstructor
import org.springframework.web.context.request.NativeWebRequest
import javax.servlet.http.HttpServletRequest

@NoArgsConstructor(access = AccessLevel.PRIVATE)
object AuthUtils {
    private const val AUTH_TOKEN_HEADER = "Authorization"

    fun extractToken(request: HttpServletRequest): String {
        return request.getHeader(AUTH_TOKEN_HEADER)?: throw AuthHeaderOmittedException()
    }

    fun extractTokenByWebRequest(webRequest: NativeWebRequest): String {
        return webRequest.getHeader(AUTH_TOKEN_HEADER)?: throw AuthHeaderOmittedException()
    }
}
