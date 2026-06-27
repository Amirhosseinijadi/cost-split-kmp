package com.costsplit.feature.expenses.data.remote

import com.costsplit.feature.expenses.data.remote.dataSource.GroupRemoteDataSource
import com.costsplit.feature.expenses.data.remote.dto.AddGroupMemberRequest
import com.costsplit.feature.expenses.data.remote.dto.CreateGroupRequest
import com.costsplit.feature.expenses.data.remote.dto.GroupBalancesResponse
import com.costsplit.feature.expenses.data.remote.dto.GroupResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal class GroupRemoteDataSourceImpl(
    private val client: HttpClient,
    private val baseUrl: String,
) : GroupRemoteDataSource {
    override suspend fun createGroup(request: CreateGroupRequest): GroupResponse =
        client.post(baseUrl.apiUrl("api/v1/groups")) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    override suspend fun getGroup(groupId: String): GroupResponse =
        client.get(baseUrl.apiUrl("api/v1/groups/$groupId")).body()

    override suspend fun addGroupMember(
        groupId: String,
        request: AddGroupMemberRequest
    ): GroupResponse =
        client.post(baseUrl.apiUrl("api/v1/groups/$groupId/members")) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    override suspend fun getGroupBalances(groupId: String): GroupBalancesResponse =
        client.get(baseUrl.apiUrl("api/v1/groups/$groupId/balances")).body()
}
