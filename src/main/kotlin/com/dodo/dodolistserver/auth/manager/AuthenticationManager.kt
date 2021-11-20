package com.dodo.dodolistserver.auth.manager

import com.dodo.dodolistserver.auth.provider.auth.AuthProvider
import com.dodo.dodolistserver.auth.vo.TokenInfo
import com.dodo.dodolistserver.user.entity.User
import com.dodo.dodolistserver.user.entity.UserType
import lombok.RequiredArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.security.InvalidParameterException
import java.util.*

@Component
@RequiredArgsConstructor
class AuthenticationManager(var authProviderMap: Map<String, AuthProvider>) {
    private val log : Logger = LoggerFactory.getLogger(this.javaClass)

    fun requestAuth(type: String, accessToken: String): TokenInfo {
        val authProvider: AuthProvider = extractProviderByType(type)
        return authProvider.auth(accessToken)
    }

    private fun extractProviderByType(type: String): AuthProvider {
        validateOAuthType(type)
        val key = getProviderKeyToType(type)

        return authProviderMap[key]!!
    }

    private fun validateOAuthType(type: String) {
        if (Objects.isNull(type)) {
            log.info("현재 제공되지 않는 OAuth type을 사용 : {$type}")
            throw InvalidParameterException("현재 제공되지 않는 OAuth 형태를 요청하였습니다.")
        }
        val providerKey: String = getProviderKeyToType(type)
        if (!authProviderMap.containsKey(providerKey)) {
            log.info("OAuth Provider와 bean과의 이름이 일치 하지 않습니다. input type : {$type}")
            throw InvalidParameterException("해당 Auth Type은 사용자에게 제공되지 않습니다 input : $type")
        }
    }

    private fun getProviderKeyToType(type: String): String {
        return type.toLowerCase() + "AuthProvider"
    }
}