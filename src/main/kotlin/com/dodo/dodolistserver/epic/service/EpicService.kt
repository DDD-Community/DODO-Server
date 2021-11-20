package com.dodo.dodolistserver.epic.service

import com.dodo.dodolistserver.epic.dto.CreateEpicRequestDto
import com.dodo.dodolistserver.epic.dto.EditEpicRequestDto
import com.dodo.dodolistserver.epic.entity.Epic
import com.dodo.dodolistserver.epic.dto.EpicResponseDto
import com.dodo.dodolistserver.epic.dto.EpicMapper
import com.dodo.dodolistserver.epic.repository.EpicRepository
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
@RequiredArgsConstructor
class EpicService @Autowired constructor(private val epicRepository: EpicRepository, private val epicMapper: EpicMapper) {

    fun createEpic(createEpicRequestDto: CreateEpicRequestDto): EpicResponseDto {

        val epicEntity = epicMapper.fromCreateRequestToEntity(createEpicRequestDto)
        val createdEpicResponse = epicMapper.toResponseDto(epicRepository.save(epicEntity))

        return createdEpicResponse
    }

    fun getEpicByEpicId(epicId: Long): EpicResponseDto? {
        val selectedEpic = epicMapper.toResponseDto(epicRepository.findById(epicId).get())

        return selectedEpic
    }

    fun getEpicByProjectId(projectId: Long): List<EpicResponseDto> {
        val projectEpic = epicMapper.toResponseDtos(epicRepository.findByProjectId(projectId))

        return projectEpic
    }

    @org.springframework.transaction.annotation.Transactional(rollbackFor=[Exception::class])
    fun editEpic(editEpicRequestDto: EditEpicRequestDto): EpicResponseDto {

        val existingEpic = epicRepository.findByIdOrNull(editEpicRequestDto.id) ?: throw RuntimeException("")

        existingEpic.dueDate = editEpicRequestDto.dueDate
        existingEpic.middleGoal = editEpicRequestDto.middleGoal

        val epicResponseDto = epicMapper.toResponseDto(existingEpic)

        return epicResponseDto
    }
}