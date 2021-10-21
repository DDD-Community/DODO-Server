package com.dodo.dodolistserver.Epic

import com.dodo.dodolistserver.Epic.Epic

interface EpicService {
    fun createEpic(epic: Epic): Epic?
    fun getEpicByEpicId(epicId: Long): Epic?
    fun getEpicByProjectId(projectId: Long): List<Epic>
    fun editEpic(epic: Epic): Epic?
}