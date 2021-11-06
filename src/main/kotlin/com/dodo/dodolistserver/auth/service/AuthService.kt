package com.dodo.dodolistserver.auth.service

import com.dodo.dodolistserver.auth.manager.AuthenticationManager
import com.dodo.dodolistserver.auth.provider.TokenProvider
import com.dodo.dodolistserver.common.exception.auth.UserNotFoundException
import com.dodo.dodolistserver.user.dto.AuthRequestDto
import com.dodo.dodolistserver.user.dto.AuthResultDto
import com.dodo.dodolistserver.user.entity.User
import com.dodo.dodolistserver.user.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class AuthService {
    private lateinit var authenticationManager: AuthenticationManager
    private lateinit var userRepository: UserRepository
    private lateinit var tokenProvider: TokenProvider


    fun authenticate(authRequestDto: AuthRequestDto): AuthResultDto {
        val existUser: User = userRepository.findByEmailAndType(authRequestDto.email, authRequestDto.userType)
            ?: throw UserNotFoundException()
        // type에 따른 인증 로직
        // Native는 password확인
        // OAuth는 valid확인 및 이메일 추출
        authenticationManager.requestAuth(existUser, authRequestDto.accessToken)

        val token: String = tokenProvider.createToken(existUser)
        return AuthResultDto(existUser.id, authRequestDto.userType, token)
    }
}