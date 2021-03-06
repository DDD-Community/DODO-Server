package com.dodo.dodolistserver.auth.advice

import com.dodo.dodolistserver.common.exception.*
import com.dodo.dodolistserver.common.exception.auth.*
import org.springframework.http.HttpStatus

import org.springframework.http.ResponseEntity

import java.security.InvalidParameterException

import lombok.RequiredArgsConstructor

import org.springframework.web.bind.annotation.RestControllerAdvice

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ExceptionHandler

@RestControllerAdvice
@RequiredArgsConstructor
class AuthAdviceController {
    private val log : Logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * 502 Bad Gateway
     * 외부 API 연동 중 에러가 발생할 경우 발생하는 Exception
     */
    @ExceptionHandler(BadGatewayException::class)
    protected fun handleBadGatewayException(exception: BadGatewayException): ResponseEntity<Any> {
        log.error(exception.message, exception)
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(exception.message)
    }

    /**
     * 404 Not Found
     * 회원가입이 안되어 있는 경우
     */
    @ExceptionHandler(UserNotFoundException::class)
    protected fun handleUserNotFoundException(exception: UserNotFoundException): ResponseEntity<Any> {
        log.error(exception.message, exception)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

    /**
     * 400 Bad Request
     * Auth 요청 정보가 적절하지 못한 경우
     */
    @ExceptionHandler(InvalidParameterException::class)
    protected fun handleParamterIsNotValidException(exception: InvalidParameterException): ResponseEntity<String> {
        log.error(exception.message, exception)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }

    /**
     * 409 Conflict
     * 이미 동일한 정보로 등록한 회원이 존재하는 경우
     */
    @ExceptionHandler(UserAlreadyExistException::class)
    protected fun handleOAuthUserExistException(exception: UserAlreadyExistException): ResponseEntity<String> {
        log.error(exception.message, exception)
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.message)
    }

    /**
     * 401 Unauthorized
     * token 혹은 password가 적절하지 않을 경우
     */
    @ExceptionHandler(
        WrongPasswordException::class,
        AuthHeaderOmittedException::class,
        TokenValidationException::class,
        TokenExpireException::class
    )
    protected fun handleUnAuthorizedException(exception: RuntimeException): ResponseEntity<String> {
        log.error(exception.message, exception)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.message)
    }

    /**
     * 403 Forbidden
     * 접근 권한이 없을 경우
     */
    @ExceptionHandler(ForbiddenException::class)
    protected fun handleForbiddenException(execException: RuntimeException): ResponseEntity<String> {
        log.error(execException.message, execException)
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(execException.message)
    }
}