package com.dodo.dodolistserver.user.repository

import com.dodo.dodolistserver.auth.getTestUser
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    @DisplayName("findByEmailAndType를 사용하여 unique한 user를 찾을 수 있다.")
    fun findByEmailAndTypeTest() {
        // given
        val expected = userRepository.save(getTestUser())

        // when
        val actual = userRepository.findByEmailAndType(getTestUser().email, getTestUser().type)

        // then
        assertEquals(expected.id, actual!!.id)
    }

    @Test
    @DisplayName("findByEmailAndType를 사용하여 존재하지 않는다면 null을 반환받을 수 있다.")
    fun findByEmailAndTypeNullTest() {
        // given

        // when
        val actual = userRepository.findByEmailAndType(getTestUser().email, getTestUser().type)

        // then
        assertEquals(null, actual)
    }
}