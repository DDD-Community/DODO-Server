package com.dodo.dodolistserver.epic.service

import com.dodo.dodolistserver.epic.dto.EpicDto
import com.dodo.dodolistserver.epic.dto.EpicMapper
import com.dodo.dodolistserver.epic.repository.EpicRepository
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
@RequiredArgsConstructor
class EpicService @Autowired constructor(private val epicRepository: EpicRepository, private val epicMapper: EpicMapper) {

    fun createEpic(epicDto: EpicDto): EpicDto? {
        val epicEntity = epicMapper.toEntity(epicDto)
        val createdEpic = epicMapper.toDto(epicRepository.save(epicEntity))

        return createdEpic
    }

    fun getEpicByEpicId(epicId: Long): EpicDto? {
        val selectedEpic = epicMapper.toDto(epicRepository.findById(epicId).get())

        return selectedEpic
    }

    fun getEpicByProjectId(projectId: Long): List<EpicDto> {
        val projectEpic = epicMapper.toDtos(epicRepository.findByProjectId(projectId))

        return projectEpic
    }

    @org.springframework.transaction.annotation.Transactional(rollbackFor=[Exception::class])
    fun editEpic(epicDto: EpicDto): EpicDto? {
        val epicEntity = epicMapper.toEntity(epicDto)
        val existEpic = epicRepository.findById(epicEntity.id)

        if(existEpic.isPresent) {
            val editedEpic = epicMapper.toDto(epicRepository.save(epicEntity))
            return editedEpic
        }
        return null
    }
}