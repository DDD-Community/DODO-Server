package com.dodo.dodolistserver.auth.provider.auth

import com.dodo.dodolistserver.auth.vo.TokenInfo
import com.dodo.dodolistserver.common.exception.BadGatewayException
import com.dodo.dodolistserver.common.exception.auth.ForbiddenException
import com.dodo.dodolistserver.user.entity.User
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
@RequiredArgsConstructor
class GoogleAuthProvider : AuthProvider {

    @Value("\${jwt.auth.google.url}")
    private lateinit var googleTokenAuthUrl: String

    override fun auth(accessToken: String): TokenInfo {
        val webClient: WebClient = WebClient.create()

        return webClient.get()
            .uri(googleTokenAuthUrl + accessToken)
            .headers { headers -> headers.setBearerAuth(accessToken) }
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError) {
                throw BadGatewayException("잘못된 kakao 토큰 ($accessToken) 입니다.")
            }
            .onStatus(HttpStatus::is5xxServerError) {
                throw BadGatewayException("google 로그인 중 에러가 발생하였습니다.")
            }
            .bodyToMono(TokenInfo::class.java)
            .block()
            ?: throw BadGatewayException("google 로그인 중 에러가 발생하였습니다.")
    }
}