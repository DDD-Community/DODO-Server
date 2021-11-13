package com.dodo.dodolistserver.epic

import com.dodo.dodolistserver.common.dto.ResponseDto
import com.dodo.dodolistserver.common.message.ResponseMessage
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Slf4j
@RestController
@RequestMapping("/api/v1/epics")
class EpicController (private val epicService: EpicService) {

    @PostMapping
    fun createEpic(@RequestBody epicDto: EpicDto): ResponseEntity<Any> {
        val createdEpic = epicService.createEpic(epicDto)
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, createdEpic))
    }

    @GetMapping(path = ["/{epicId}"])
    fun getEpicByEpicId(@PathVariable("epicId") epicId: Long): ResponseEntity<Any> {
        val selectedEpic = epicService.getEpicByEpicId(epicId)
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, selectedEpic))
    }

    @GetMapping(path = ["/project/{projectId}"])
    fun getEpicByProjectId(@PathVariable("projectId") projectId: Long): ResponseEntity<Any> {
        val projectEpics = epicService.getEpicByProjectId(projectId)
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, projectEpics))
    }

    @PutMapping
    fun editEpic(@RequestBody epicDto: EpicDto): ResponseEntity<Any> {
        val editedEpic = epicService.editEpic(epicDto)
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, editedEpic))
    }
}