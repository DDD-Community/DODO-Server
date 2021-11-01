package com.dodo.dodolistserver.auth.config

import com.dodo.dodolistserver.auth.JWTTokenProvider
import com.dodo.dodolistserver.auth.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AuthConfig {
    @Bean
    fun tokenProvider(): TokenProvider {
        return JWTTokenProvider()
    }
}