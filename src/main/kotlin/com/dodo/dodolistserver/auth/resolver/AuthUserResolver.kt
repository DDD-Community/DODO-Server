package com.dodo.dodolistserver.auth.resolver

import com.dodo.dodolistserver.user.repository.UserRepository

import com.dodo.dodolistserver.auth.provider.TokenProvider
import com.dodo.dodolistserver.auth.util.AuthUtils
import com.dodo.dodolistserver.auth.util.AuthUtils.extractTokenByWebRequest
import com.dodo.dodolistserver.common.exception.auth.*
import com.dodo.dodolistserver.user.entity.User

import lombok.RequiredArgsConstructor

import lombok.extern.slf4j.Slf4j
import org.springframework.core.MethodParameter
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpServletRequest

@Slf4j
@Component
@RequiredArgsConstructor
class AuthUserResolver : HandlerMethodArgumentResolver {
    private lateinit var httpServletRequest: HttpServletRequest
    private lateinit var tokenProvider: TokenProvider
    private lateinit var userRepository: UserRepository


    override fun supportsParameter(parameter: MethodParameter): Boolean {
        val hasAnnotation = parameter.getParameterAnnotation(AuthUser::class.java) != null
        val isMatchType: Boolean = (parameter.parameterType == User::class.java)

        // token 체크
        AuthUtils.extractToken(httpServletRequest)
        return hasAnnotation && isMatchType
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): User {
        val authToken: String = extractTokenByWebRequest(webRequest)

        tokenProvider.isValidToken(authToken)

        val userId = tokenProvider.extractIdByToken(authToken)
        return userRepository.findByIdOrNull(userId)?: throw UserNotFoundException()
    }
}