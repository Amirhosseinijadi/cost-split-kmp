package com.costsplit.feature.groups.domain.usecase

import com.costsplit.feature.groups.domain.repository.GroupRepository

class GetUserGroupsUseCase(
    private val repository: GroupRepository,
) {
    suspend operator fun invoke(userId: String) = repository.listUserGroups(userId)
}
