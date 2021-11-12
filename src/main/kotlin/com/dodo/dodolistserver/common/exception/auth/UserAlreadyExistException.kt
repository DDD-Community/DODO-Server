package com.dodo.dodolistserver.common.exception.auth

import com.dodo.dodolistserver.common.exception.ConflictException

private const val USER_ALREADY_EXIST_ERR_MSG: String = "이미 존재하는 유저입니다."

class UserAlreadyExistException(msg: String = USER_ALREADY_EXIST_ERR_MSG) : ConflictException(msg)