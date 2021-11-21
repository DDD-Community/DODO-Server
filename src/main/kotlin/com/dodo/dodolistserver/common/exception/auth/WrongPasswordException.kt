package com.dodo.dodolistserver.common.exception.auth

private const val WRONG_PASSWORD_EXCEPTION_MSG = "Password가 잘못되었습니다."

class WrongPasswordException() : RuntimeException(WRONG_PASSWORD_EXCEPTION_MSG)