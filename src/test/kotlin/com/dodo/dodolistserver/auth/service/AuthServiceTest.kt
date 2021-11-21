package com.dodo.dodolistserver.auth.service

import com.dodo.dodolistserver.auth.TEST_USER_PASSWORD
import com.dodo.dodolistserver.auth.getTestCreateUserRequestDto
import com.dodo.dodolistserver.auth.getTestUser
import com.dodo.dodolistserver.auth.provider.auth.GoogleAuthProvider
import com.dodo.dodolistserver.auth.vo.TokenInfo
import com.dodo.dodolistserver.common.exception.auth.UserAlreadyExistException
import com.dodo.dodolistserver.common.exception.auth.UserNotFoundException
import com.dodo.dodolistserver.common.exception.auth.WrongPasswordException
import com.dodo.dodolistserver.user.dto.NativeAuthRequestDto
import com.dodo.dodolistserver.user.dto.OAuthRequestDto
import com.dodo.dodolistserver.user.entity.UserType
import com.dodo.dodolistserver.user.repository.UserRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.transaction.annotation.Transactional
import java.security.InvalidParameterException

@SpringBootTest
@Transactional
internal class AuthServiceTest {

    @MockBean
    lateinit var googleAuthProvider: GoogleAuthProvider

    @Autowired
    lateinit var authService: AuthService

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    @DisplayName("OAuth의 인증을 수행할 수 있다.")
    fun oAuthAuthenticateTest() {
        // given
        val givenUser = userRepository.save(getTestUser(UserType.GOOGLE))
        val expected = TokenInfo(givenUser.email)

        BDDMockito.willReturn(expected)
            .given(googleAuthProvider)
            .auth(anyString())

        // when
        val actual = authService.oAuthAuthenticate("google", OAuthRequestDto("123"))

        // then
        assertEquals(givenUser.id, actual.userId)
    }

    @Test
    @DisplayName("잘못된 Type의 OAuth를 받으면 예외를 반환할 수 있다.")
    fun oAuthAuthenticateExceptionTest() {
        // given
        val givenUser = userRepository.save(getTestUser(UserType.GOOGLE))
        val expected = TokenInfo(givenUser.email)

        BDDMockito.willReturn(expected)
            .given(googleAuthProvider)
            .auth(anyString())

        // then
        assertThrows(InvalidParameterException::class.java) {
            authService.oAuthAuthenticate("wrongType", OAuthRequestDto("123"))
        }
    }

    @Test
    @DisplayName("OAuth의 유저가 존재하지 않는다면 유저를 생성할 수 있다.")
    fun oAuthAuthenticateCreateTest() {
        // given
        val givenUser = getTestUser(UserType.GOOGLE)
        val expected = TokenInfo(givenUser.email)

        BDDMockito.willReturn(expected)
            .given(googleAuthProvider)
            .auth(anyString())

        // when
        authService.oAuthAuthenticate("google", OAuthRequestDto("123"))
        val actual = userRepository.findByEmailAndType(givenUser.email, givenUser.type)

        // then
        assertNotEquals(null, actual)

        // TODO : User Name Gen 생성 시 변경
        assertEquals("김DODO", actual!!.name)
    }

    @Test
    @DisplayName("Native유저의 인증을 진행할 수 있다.")
    fun nativeAuthenticateTest() {
        // given
        val expected = userRepository.save(getTestUser())

        // when
        val actual = authService.nativeAuthenticate(
            NativeAuthRequestDto(
                expected.email,
                TEST_USER_PASSWORD
            )
        )

        // then
        assertEquals(expected.id, actual.userId)
    }

    @Test
    @DisplayName("Native유저의 정보가 없을 경우 예외를 반환할 수 있다.")
    fun nativeAuthenticateNotFoundTest() {
        // given
        val expected = getTestUser()

        // then
        assertThrows(UserNotFoundException::class.java) {
            authService.nativeAuthenticate(
                NativeAuthRequestDto(
                    expected.email,
                    TEST_USER_PASSWORD
                )
            )
        }
    }

    @Test
    @DisplayName("Native유저의 Password정보가 다르다면 예외를 반환할 수 있다.")
    fun nativeAuthenticateWrongPasswordTest() {
        // given
        val expected = userRepository.save(getTestUser())

        // then
        assertThrows(WrongPasswordException::class.java) {
            authService.nativeAuthenticate(
                NativeAuthRequestDto(
                    expected.email,
                    TEST_USER_PASSWORD + "wrongPassword"
                )
            )
        }
    }

    @Test
    @DisplayName("Native User의 회원가입을 수행할 수 있다.")
    fun signUpTest() {
        // given
        val expected = getTestCreateUserRequestDto()

        // when
        val actual = authService.signUp(expected)
        val savedData = userRepository.findByEmailAndType(expected.email, UserType.NATIVE)

        // then
        assertNotEquals(null, actual.userId)
        assertEquals(expected.email, savedData!!.email)
        assertEquals(expected.name, savedData.name)
    }

    @Test
    @DisplayName("SignUp시 User가 이미 존재한다면 예외를 반환할 수 있다.")
    fun signUpAlreadyExistTest() {
        // given
        val a = userRepository.save(getTestUser())
        val expected = getTestCreateUserRequestDto()
        val savedData = userRepository.findByEmailAndType(expected.email, UserType.NATIVE)



        // then
        assertThrows(UserAlreadyExistException::class.java){
            authService.signUp(expected)
        }

    }
}