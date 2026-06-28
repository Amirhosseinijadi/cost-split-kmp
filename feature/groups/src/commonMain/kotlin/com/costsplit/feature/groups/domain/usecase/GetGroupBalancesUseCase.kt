package com.costsplit.feature.groups.domain.usecase

import com.costsplit.feature.groups.domain.repository.GroupRepository

class GetGroupBalancesUseCase(
    private val repository: GroupRepository,
) {
    suspend operator fun invoke(groupId: String) = repository.getGroupBalances(groupId)
}
