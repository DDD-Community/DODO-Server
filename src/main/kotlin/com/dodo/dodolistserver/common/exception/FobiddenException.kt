package com.dodo.dodolistserver.common.exception

private const val FORIBIDDEN_EXCECPTION_MSG = "허용되지 않은 사용자 입니다."

class ForbiddenException : RuntimeException(FORIBIDDEN_EXCECPTION_MSG)