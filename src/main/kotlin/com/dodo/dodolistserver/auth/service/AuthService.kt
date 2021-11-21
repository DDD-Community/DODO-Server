package com.dodo.dodolistserver.auth.service

import com.dodo.dodolistserver.auth.manager.AuthenticationManager
import com.dodo.dodolistserver.auth.provider.TokenProvider
import com.dodo.dodolistserver.common.exception.auth.UserAlreadyExistException
import com.dodo.dodolistserver.common.exception.auth.UserNotFoundException
import com.dodo.dodolistserver.common.exception.auth.WrongPasswordException
import com.dodo.dodolistserver.user.dto.NativeAuthRequestDto
import com.dodo.dodolistserver.user.dto.AuthResultDto
import com.dodo.dodolistserver.user.dto.CreateUserRequestDto
import com.dodo.dodolistserver.user.dto.OAuthRequestDto
import com.dodo.dodolistserver.user.entity.User
import com.dodo.dodolistserver.user.entity.UserType
import com.dodo.dodolistserver.user.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.mindrot.jbcrypt.BCrypt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.security.InvalidParameterException

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val userRepository: UserRepository,
    private val tokenProvider: TokenProvider
) {


    @Transactional
    fun oAuthAuthenticate(type: String, authRequestDto: OAuthRequestDto): AuthResultDto {
        val info = authenticationManager.requestAuth(type, authRequestDto.accessToken)

        val userType = try {
            UserType.valueOf(type.toUpperCase())
        } catch (e: IllegalArgumentException) {
            throw InvalidParameterException("현재 제공되지 않는 OAuth 형태를 요청하였습니다.")
        }

        val existUser: User = userRepository.findByEmailAndType(info.email, userType)
            ?: userRepository.save(
                    User(
                        email = info.email,
                        //TODO:이름 생성기
                        name = "김DODO",
                        type = userType
                    )
                )

        val token: String = tokenProvider.createToken(existUser)

        existUser.updateLastLogin()

        return AuthResultDto(
            userId = existUser.id!!,
            userType = existUser.type,
            token = token
        )
    }

    @Transactional
    fun nativeAuthenticate(authRequestDto: NativeAuthRequestDto): AuthResultDto {
        val existUser: User = userRepository.findByEmailAndType(authRequestDto.email, UserType.NATIVE)
            ?: throw UserNotFoundException()

        if (!existUser.checkPassword(authRequestDto.password)) throw WrongPasswordException()

        val token: String = tokenProvider.createToken(existUser)

        existUser.updateLastLogin()

        return AuthResultDto(
            userId = existUser.id!!,
            userType = existUser.type,
            token = token
        )
    }

    @Transactional
    fun signUp(request: CreateUserRequestDto): AuthResultDto {
        val tmp = userRepository.findByEmailAndType(request.email, UserType.NATIVE)

        tmp?.let {
            throw UserAlreadyExistException()
        }


        val newUser = User(
            email = request.email,
            name = request.name,
            type = UserType.NATIVE,
            password = BCrypt.hashpw(request.password, BCrypt.gensalt()),
            birth = request.birth
        )

        val saveUser = userRepository.save(newUser)
        val token = tokenProvider.createToken(saveUser)

        return AuthResultDto(
            userId = saveUser.id!!,
            userType = saveUser.type,
            token = token
        )
    }
}