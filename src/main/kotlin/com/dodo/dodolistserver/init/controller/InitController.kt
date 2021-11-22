package com.dodo.dodolistserver.init.controller

import com.dodo.dodolistserver.common.dto.ResponseDto
import com.dodo.dodolistserver.common.message.ResponseMessage
import com.dodo.dodolistserver.init.dto.CreateIntensiveRequestDto
import com.dodo.dodolistserver.init.service.InitService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/inits")
class InitController(private val initService: InitService) {

    @PostMapping(path = ["/Intensively"])
    @ApiOperation(value = "'집중적으로 빡세게' 신규 Project 생성", notes = "서비스 초기 시작 Project를 생성한다.")
    fun createIntensiveProject (@RequestBody createIntensiveRequestDto: CreateIntensiveRequestDto): ResponseEntity<Any> {
        val createdProject = initService.createIntensiveProject(createIntensiveRequestDto)

        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, createdProject))
    }
}