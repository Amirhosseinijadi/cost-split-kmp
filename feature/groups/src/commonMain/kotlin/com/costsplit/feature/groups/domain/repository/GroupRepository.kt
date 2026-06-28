package com.costsplit.feature.groups.domain.repository

import com.costsplit.core.common.result.AppResult
import com.costsplit.feature.groups.domain.model.Group
import com.costsplit.feature.groups.domain.model.GroupBalances
import com.costsplit.feature.groups.domain.model.NewGroup

interface GroupRepository {
    suspend fun listUserGroups(userId: String): AppResult<List<Group>>
    suspend fun createGroup(group: NewGroup): AppResult<Group>
    suspend fun getGroup(groupId: String): AppResult<Group>
    suspend fun getGroupBalances(groupId: String): AppResult<GroupBalances>
}
