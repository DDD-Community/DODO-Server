package com.dodo.dodolistserver.common.exception

private const val DATA_CONFLICT_EXCEPTION_MSG = "데이터의 충돌이 발생했습니다."

open class ConflictException(msg: String?) : RuntimeException(msg ?: DATA_CONFLICT_EXCEPTION_MSG)