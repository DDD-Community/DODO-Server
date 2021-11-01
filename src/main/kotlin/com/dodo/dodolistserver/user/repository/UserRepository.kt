package com.dodo.dodolistserver.user.repository

import com.dodo.dodolistserver.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
}