package com.costsplit.feature.groups.data.remote.dataSource

import com.costsplit.feature.groups.data.remote.request.group.AddGroupMemberRequest
import com.costsplit.feature.groups.data.remote.request.group.CreateGroupRequest
import com.costsplit.feature.groups.data.remote.response.group.GroupBalancesResponse
import com.costsplit.feature.groups.data.remote.response.group.GroupResponse

interface GroupRemoteDataSource {
    suspend fun createGroup(request: CreateGroupRequest): GroupResponse
    suspend fun getGroup(groupId: String): GroupResponse
    suspend fun addGroupMember(groupId: String, request: AddGroupMemberRequest): GroupResponse
    suspend fun getGroupBalances(groupId: String): GroupBalancesResponse
}
