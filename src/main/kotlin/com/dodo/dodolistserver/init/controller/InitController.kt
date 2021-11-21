package com.dodo.dodolistserver.init.controller

import com.dodo.dodolistserver.common.dto.ResponseDto
import com.dodo.dodolistserver.common.message.ResponseMessage
import com.dodo.dodolistserver.init.dto.CreateIntensiveRequestDto
import com.dodo.dodolistserver.init.service.InitService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/inits")
class InitController(private val initService: InitService) {

    @PostMapping(path = ["/Intensively"])
    fun createIntensiveProject (@RequestBody createIntensiveRequestDto: CreateIntensiveRequestDto): ResponseEntity<Any> {
        val createdProject = initService.createIntensiveProject(createIntensiveRequestDto)

        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, createdProject))
    }
}