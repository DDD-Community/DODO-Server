package com.dodo.dodolistserver.user.repository

import com.dodo.dodolistserver.user.entity.User
import com.dodo.dodolistserver.user.entity.UserType
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmailAndType(email: String, type: UserType): User?
}