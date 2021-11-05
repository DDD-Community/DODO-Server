package com.dodo.dodolistserver.epic

import com.dodo.dodolistserver.epic.Epic

interface EpicService {
    fun createEpic(epic: Epic): EpicDto?
    fun getEpicByEpicId(epicId: Long): EpicDto?
    fun getEpicByProjectId(projectId: Long): List<EpicDto>
    fun editEpic(epic: Epic): EpicDto?
}