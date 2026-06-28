package com.costsplit.feature.groups.domain.usecase

import com.costsplit.feature.groups.domain.model.NewGroup
import com.costsplit.feature.groups.domain.repository.GroupRepository

class CreateGroupUseCase(
    private val repository: GroupRepository,
) {
    suspend operator fun invoke(group: NewGroup) = repository.createGroup(group)
}
