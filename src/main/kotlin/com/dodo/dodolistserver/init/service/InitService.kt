package com.dodo.dodolistserver.init.service

import com.dodo.dodolistserver.epic.repository.EpicRepository
import com.dodo.dodolistserver.init.dto.CreateIntensiveRequestDto
import com.dodo.dodolistserver.init.dto.InitIntensivelyResponseDto
import com.dodo.dodolistserver.init.dto.InitMapper
import com.dodo.dodolistserver.project.dto.ProjectMapper
import com.dodo.dodolistserver.project.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class InitService(private val projectRepository: ProjectRepository, private val epicRepository: EpicRepository, private val initMapper: InitMapper) {
    @org.springframework.transaction.annotation.Transactional(rollbackFor=[Exception::class])
    fun createIntensiveProject(createIntensiveRequestDto: CreateIntensiveRequestDto): InitIntensivelyResponseDto{

        val requestProjectEntity = initMapper.fromCreateProjectRequestToEntity(createIntensiveRequestDto.createProjectRequestDto)
        val createdProjectInfo = projectRepository.save(requestProjectEntity)
        val initProjectResponseDto = initMapper.toProjectResponseDto(createdProjectInfo)
        val projectId = createdProjectInfo.id

        val requestEpics = createIntensiveRequestDto.createEpicRequestDtos
        for(requestEpic in requestEpics) {
            requestEpic.projectId = projectId
        }
        val requestEpicEntities = initMapper.fromCreateEpicRequestToEntities(requestEpics)
        val createdEpicInfos = epicRepository.saveAll(requestEpicEntities)
        val initEpicResponseDto = initMapper.toEpicResponseDtos(createdEpicInfos)

        val initResponseDto = InitIntensivelyResponseDto(initProjectResponseDto, initEpicResponseDto)

        return initResponseDto
    }
}