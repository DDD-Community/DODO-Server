package com.dodo.dodolistserver.auth.util

import com.dodo.dodolistserver.common.exception.auth.AuthHeaderOmittedException
import com.dodo.dodolistserver.common.exception.auth.ForbiddenException
import com.dodo.dodolistserver.user.entity.User
import lombok.AccessLevel
import lombok.NoArgsConstructor
import org.springframework.web.context.request.NativeWebRequest
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@NoArgsConstructor(access = AccessLevel.PRIVATE)
object AuthUtils {
    private const val AUTH_TOKEN_HEADER = "Authorization"

    fun extractToken(request: HttpServletRequest): String {
        return request.getHeader(AUTH_TOKEN_HEADER)?: throw AuthHeaderOmittedException()
    }

    fun extractTokenByWebRequest(webRequest: NativeWebRequest): String {
        return webRequest.getHeader(AUTH_TOKEN_HEADER)?: throw AuthHeaderOmittedException()
    }

    fun setTokenInHeader(response: HttpServletResponse, token: String) {
        response.setHeader(AUTH_TOKEN_HEADER, token)
    }

    fun validateUser(givenUserId: Long, tokenUser: User) {
        if (tokenUser.id != givenUserId) throw ForbiddenException()
    }
}
