package com.dodo.dodolistserver.auth

import com.dodo.dodolistserver.user.entity.User

interface TokenProvider {
    fun createToken(user: User): String
    fun extractIdByToken(token: String): Long
    fun isValidToken(token: String): Boolean
}