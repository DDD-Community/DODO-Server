package com.dodo.dodolistserver.auth.provider.auth

import com.dodo.dodolistserver.auth.vo.TokenInfo

interface AuthProvider {
    fun auth(accessToken: String): TokenInfo
}