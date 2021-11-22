package com.dodo.dodolistserver.epic.controller

import com.dodo.dodolistserver.common.dto.ResponseDto
import com.dodo.dodolistserver.common.message.ResponseMessage
import com.dodo.dodolistserver.epic.dto.CreateEpicRequestDto
import com.dodo.dodolistserver.epic.dto.EditEpicRequestDto
import com.dodo.dodolistserver.epic.dto.EpicResponseDto
import com.dodo.dodolistserver.epic.service.EpicService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Slf4j
@RestController
@RequestMapping("/api/v1/epics")
@Api(value = "Epic API")
class EpicController (private val epicService: EpicService) {

    @PostMapping
    @ApiOperation(value = "Epic 생성", notes = "신규 Epic을 생성한다. (중기 목표, Project 하위)")
    fun createEpic(@RequestBody createEpicRequestDto: CreateEpicRequestDto): ResponseEntity<Any> {
        val createdEpic = epicService.createEpic(createEpicRequestDto)
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, createdEpic))
    }

    @GetMapping(path = ["/{epicId}"])
    @ApiOperation(value = "Epic 조회 - Epic ID", notes = "Epic ID를 기반으로 Epic을 검색한다.")
    fun getEpicByEpicId(@PathVariable("epicId") epicId: Long): ResponseEntity<Any> {
        val selectedEpic = epicService.getEpicByEpicId(epicId)
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, selectedEpic))
    }

    @GetMapping(path = ["/project/{projectId}"])
    @ApiOperation(value = "Epic 조회 - Project ID", notes = "Project Id기반으로 Epic 목록을 검색한다.")
    fun getEpicByProjectId(@PathVariable("projectId") projectId: Long): ResponseEntity<Any> {
        val projectEpics = epicService.getEpicByProjectId(projectId)
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, projectEpics))
    }

    @PutMapping
    @ApiOperation(value = "Epic 수정", notes = "Epic을 수정한다.")
    fun editEpic(@RequestBody editEpicRequestDto: EditEpicRequestDto): ResponseEntity<Any> {
        val editedEpic = epicService.editEpic(editEpicRequestDto)
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, editedEpic))
    }
}