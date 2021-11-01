package com.dodo.dodolistserver.common.exception.auth

private const val TOKEN_EXPIRE_ERR_MSG = "Token이 만료가 되었습니다."

class TokenExpireException(): RuntimeException(TOKEN_EXPIRE_ERR_MSG)