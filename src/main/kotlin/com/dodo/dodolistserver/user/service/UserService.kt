package com.dodo.dodolistserver.user.service

import com.dodo.dodolistserver.user.dto.GetUserDataResponseDto
import com.dodo.dodolistserver.user.dto.UpdateUserProfileRequestDto
import com.dodo.dodolistserver.user.dto.UserProjectDataResponseDto
import com.dodo.dodolistserver.user.entity.User
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class UserService {

    fun getUserDate(user: User): GetUserDataResponseDto {
        val sortedProjects = user.projects.sortedBy { it.createdAt }

        return GetUserDataResponseDto(
            id = user.id!!,
            name = user.name,
            projects = sortedProjects.map { project ->
                UserProjectDataResponseDto(
                    projectId = project.id,
                    projectName = project.goal
                )
            }
        )
    }

    @Transactional
    fun updatePassword(user: User, newPassword: String) {
        user.updatePassword(newPassword)
    }

    @Transactional
    fun updateUserProfile(user: User, request: UpdateUserProfileRequestDto) {
        user.updateUserProfile(request.name, request.birth)
    }

}