package com.dodo.dodolistserver.common.exception.auth

private const val TOKEN_VALIDATION_ERR_MSG: String = "Token이 validate하지 않습니다."

class TokenValidationException : RuntimeException(TOKEN_VALIDATION_ERR_MSG)