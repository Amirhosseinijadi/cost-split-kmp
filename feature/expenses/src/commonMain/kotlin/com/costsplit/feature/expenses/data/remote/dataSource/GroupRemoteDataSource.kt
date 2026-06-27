package com.costsplit.feature.expenses.data.remote.dataSource

import com.costsplit.feature.expenses.data.remote.dto.AddGroupMemberRequest
import com.costsplit.feature.expenses.data.remote.dto.CreateGroupRequest
import com.costsplit.feature.expenses.data.remote.dto.GroupBalancesResponse
import com.costsplit.feature.expenses.data.remote.dto.GroupResponse

interface GroupRemoteDataSource {
    suspend fun createGroup(request: CreateGroupRequest): GroupResponse
    suspend fun getGroup(groupId: String): GroupResponse
    suspend fun addGroupMember(groupId: String, request: AddGroupMemberRequest): GroupResponse
    suspend fun getGroupBalances(groupId: String): GroupBalancesResponse
}