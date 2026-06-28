package com.costsplit.feature.groups.data.remote.dataSource

import com.costsplit.core.common.result.AppResult
import com.costsplit.feature.groups.data.remote.request.group.AddGroupMemberRequest
import com.costsplit.feature.groups.data.remote.request.group.CreateGroupRequest
import com.costsplit.feature.groups.data.remote.response.group.GroupBalancesResponse
import com.costsplit.feature.groups.data.remote.response.group.GroupResponse

interface GroupRemoteDataSource {
    suspend fun listUserGroups(userId: String): AppResult<List<GroupResponse>>
    suspend fun createGroup(request: CreateGroupRequest): AppResult<GroupResponse>
    suspend fun getGroup(groupId: String): AppResult<GroupResponse>
    suspend fun addGroupMember(groupId: String, request: AddGroupMemberRequest): AppResult<GroupResponse>
    suspend fun getGroupBalances(groupId: String): AppResult<GroupBalancesResponse>
}
