package com.costsplit.feature.groups.data.repository

import com.costsplit.core.common.result.AppResult
import com.costsplit.core.common.result.map
import com.costsplit.feature.groups.data.mapper.toDomain
import com.costsplit.feature.groups.data.mapper.toRequest
import com.costsplit.feature.groups.data.remote.dataSource.GroupRemoteDataSource
import com.costsplit.feature.groups.domain.model.Group
import com.costsplit.feature.groups.domain.model.GroupBalances
import com.costsplit.feature.groups.domain.model.NewGroup
import com.costsplit.feature.groups.domain.repository.GroupRepository

internal class GroupRepositoryImpl(
    private val remoteDataSource: GroupRemoteDataSource,
) : GroupRepository {
    override suspend fun listUserGroups(userId: String): AppResult<List<Group>> =
        remoteDataSource.listUserGroups(userId).map { groups -> groups.map { it.toDomain() } }

    override suspend fun createGroup(group: NewGroup): AppResult<Group> =
        remoteDataSource.createGroup(group.toRequest()).map { it.toDomain() }

    override suspend fun getGroup(groupId: String): AppResult<Group> =
        remoteDataSource.getGroup(groupId).map { it.toDomain() }

    override suspend fun getGroupBalances(groupId: String): AppResult<GroupBalances> =
        remoteDataSource.getGroupBalances(groupId).map { it.toDomain() }
}
