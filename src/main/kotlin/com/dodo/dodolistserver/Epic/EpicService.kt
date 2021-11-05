package com.dodo.dodolistserver.Epic

import com.dodo.dodolistserver.Epic.Epic

interface EpicService {
    fun createEpic(epic: Epic): EpicDto?
    fun getEpicByEpicId(epicId: Long): EpicDto?
    fun getEpicByProjectId(projectId: Long): List<EpicDto>
    fun editEpic(epic: Epic): EpicDto?
}