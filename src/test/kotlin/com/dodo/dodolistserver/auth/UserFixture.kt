package com.dodo.dodolistserver.auth

import com.dodo.dodolistserver.user.dto.CreateUserRequestDto
import com.dodo.dodolistserver.user.entity.User
import com.dodo.dodolistserver.user.entity.UserType
import org.mindrot.jbcrypt.BCrypt

const val TEST_USER_PASSWORD = "123"

private const val TEST_USER_NAME = "김두두"
private const val TEST_USER_EMAIL = "test@test.com"
private const val TEST_USER_BIRTH = "090909"

fun getTestUser(type: UserType = UserType.NATIVE) = User(
    id = 1,
    email = TEST_USER_EMAIL,
    name = TEST_USER_NAME,
    type = type,
    password = BCrypt.hashpw(TEST_USER_PASSWORD, BCrypt.gensalt()),
    birth = TEST_USER_BIRTH
)

fun getTestCreateUserRequestDto() = CreateUserRequestDto(
    name = TEST_USER_NAME,
    email = TEST_USER_EMAIL,
    birth = TEST_USER_BIRTH,
    password = TEST_USER_PASSWORD
)