package com.dodo.dodolistserver.auth.interceptor

import javax.servlet.http.HttpServletRequest

import javax.servlet.http.HttpServletResponse

import com.dodo.dodolistserver.auth.provider.TokenProvider
import com.dodo.dodolistserver.auth.util.AuthUtils

import org.springframework.web.servlet.HandlerInterceptor

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component


@Component
@RequiredArgsConstructor
class AuthInterceptor : HandlerInterceptor {
    private lateinit var tokenProvider: TokenProvider

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        return validateAccessToken(request)
//        return true
    }

    private fun validateAccessToken(request: HttpServletRequest): Boolean {
        val accessToken: String = AuthUtils.extractToken(request)
        return tokenProvider.isValidToken(accessToken)
    }
}