package com.dodo.dodolistserver.auth.provider.auth

import com.dodo.dodolistserver.user.entity.User

interface AuthProvider {
    fun auth(user: User, accessToken: String): User
}