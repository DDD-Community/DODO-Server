package com.dodo.dodolistserver.common.exception.auth

import com.dodo.dodolistserver.common.exception.ConflictException

class OAuthExistException(msg: String?) : ConflictException(msg)