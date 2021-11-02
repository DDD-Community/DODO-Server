package com.dodo.dodolistserver.Epic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class EpicServiceImpl : EpicService{

    @Autowired
    private lateinit var epicRepository: EpicRepository

    override fun createEpic(epic: Epic): Epic? {
        val createdEpic = epicRepository.save(epic)

        return createdEpic
    }

    override fun getEpicByEpicId(epicId: Long): Epic? {
        val selectedEpic = epicRepository.findById(epicId).get()

        return selectedEpic
    }

    override fun getEpicByProjectId(projectId: Long): List<Epic> {
        val projectEpic = epicRepository.findByProjectId(projectId)

        return projectEpic
    }

    @org.springframework.transaction.annotation.Transactional(rollbackFor=[Exception::class])
    override fun editEpic(epic: Epic): Epic? {
        val existEpic = epicRepository.findById(epic.id)

        if(existEpic.isPresent) {
            epicRepository.save(epic)
            return epic
        }
        return null
    }
}