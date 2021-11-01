package com.dodo.dodolistserver.common.exception

private const val USER_NOT_FOUND_EXCEPTION_MSG = "등록된 회원이 아닙니다."

class UserNotFoundException() : NotFoundException(USER_NOT_FOUND_EXCEPTION_MSG)