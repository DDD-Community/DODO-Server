package com.dodo.dodolistserver.common.exception.auth

private const val AUTH_HEADER_OMITTED_EXCEPTION_MSG = "사용자 권한 요청정보가 없습니다."

class AuthHeaderOmittedException() : RuntimeException(AUTH_HEADER_OMITTED_EXCEPTION_MSG)