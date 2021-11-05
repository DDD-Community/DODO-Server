package com.dodo.dodolistserver.Epic

import lombok.RequiredArgsConstructor
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
@RequiredArgsConstructor
class EpicServiceImpl @Autowired constructor(
    private val epicRepository: EpicRepository,
    private val epicMapper: EpicMapper
) : EpicService{

    override fun createEpic(epic: Epic): EpicDto? {
        val createdEpic = epicMapper.toDto(epicRepository.save(epic))

        return createdEpic
    }

    override fun getEpicByEpicId(epicId: Long): EpicDto? {
        val selectedEpic = epicMapper.toDto(epicRepository.findById(epicId).get())

        return selectedEpic
    }

    override fun getEpicByProjectId(projectId: Long): List<EpicDto> {
        val projectEpic = epicMapper.toDtos(epicRepository.findByProjectId(projectId))

        return projectEpic
    }

    @org.springframework.transaction.annotation.Transactional(rollbackFor=[Exception::class])
    override fun editEpic(epic: Epic): EpicDto? {
        val existEpic = epicRepository.findById(epic.id)

        if(existEpic.isPresent) {
            val editedEpic = epicMapper.toDto(epicRepository.save(epic))
            return editedEpic
        }
        return null
    }
}