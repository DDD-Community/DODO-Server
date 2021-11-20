package com.dodo.dodolistserver.user

import com.dodo.dodolistserver.auth.resolver.AuthUser
import com.dodo.dodolistserver.auth.service.AuthService
import com.dodo.dodolistserver.auth.util.AuthUtils
import com.dodo.dodolistserver.user.dto.*
import com.dodo.dodolistserver.user.entity.User
import com.dodo.dodolistserver.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
class UserController(
    private val authService: AuthService,
    private val userService: UserService,
) {
    @PostMapping("/api/v1/auth")
    fun authenticate(
        @RequestBody @Valid authRequestDto: NativeAuthRequestDto,
        response: HttpServletResponse,
    ): ResponseEntity<AuthorizationDto> {
        val res: AuthResultDto = authService.nativeAuthenticate(authRequestDto)

        return ResponseEntity.ok(
            AuthorizationDto(res.token)
        )
    }

    @PostMapping("/api/v1/auth/{type}")
    fun oAuthenticate(
        @RequestBody @Valid authRequestDto: OAuthRequestDto,
        @PathVariable type: String,
        response: HttpServletResponse
    ): ResponseEntity<AuthorizationDto> {
        val res: AuthResultDto = authService.oAuthAuthenticate(type, authRequestDto)

        return ResponseEntity.ok(
            AuthorizationDto(res.token)
        )
    }

    @PostMapping("/api/v1/auth/create")
    fun signUp(
        @RequestBody @Valid creationRequestDto: CreateUserRequestDto,
        response: HttpServletResponse,
    ): ResponseEntity<Void> {
        val res: AuthResultDto = authService.signUp(creationRequestDto)
        AuthUtils.setTokenInHeader(response, res.token)

        return ResponseEntity.ok().build()
    }

    @PutMapping("/api/v1/user/password")
    fun updatePassword(
        @AuthUser user: User,
        @RequestBody @Valid updatePasswordDto: UpdatePasswordDto
    ): ResponseEntity<Void> {
        AuthUtils.validateUser(updatePasswordDto.userId, user)

        userService.updatePassword(updatePasswordDto.newPassword)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/api/v1/user")
    fun getUserData(@AuthUser user: User): ResponseEntity<GetUserDataResponseDto> {
        val response = userService.getUserDate(user)

        return ResponseEntity.ok(response)
    }

    @GetMapping("/api/v1/user/profile")
    fun getUserProfile(@AuthUser user: User): ResponseEntity<GetUserProfileResponseDto> {
        val response = GetUserProfileResponseDto(
            id = user.id!!,
            type = user.type,
            email = user.email,
            name = user.name,
            birth = user.birth!!
        )

        return ResponseEntity.ok(response)
    }
}