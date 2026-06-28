package com.costsplit.feature.groups.domain.usecase

import com.costsplit.feature.groups.domain.repository.GroupRepository

class GetGroupUseCase(
    private val repository: GroupRepository,
) {
    suspend operator fun invoke(groupId: String) = repository.getGroup(groupId)
}
