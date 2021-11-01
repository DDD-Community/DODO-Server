package com.dodo.dodolistserver.auth.util

import com.dodo.dodolistserver.common.exception.ForbiddenException
import com.dodo.dodolistserver.user.entity.User
import lombok.AccessLevel
import lombok.NoArgsConstructor
import org.springframework.web.context.request.NativeWebRequest
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@NoArgsConstructor(access = AccessLevel.PRIVATE)
object AuthUtils {
    private const val AUTH_TOKEN_HEADER = "Authorization"

    fun extractToken(request: HttpServletRequest): String {
        return request.getHeader(AUTH_TOKEN_HEADER)
    }
}
