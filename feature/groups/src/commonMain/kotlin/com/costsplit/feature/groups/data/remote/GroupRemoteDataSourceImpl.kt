package com.costsplit.feature.groups.data.remote

import com.costsplit.core.common.coroutine.AppDispatchers
import com.costsplit.core.common.result.AppResult
import com.costsplit.core.network.remote.BaseRemoteDataSource
import com.costsplit.core.network.remote.apiUrl
import com.costsplit.feature.groups.data.remote.dataSource.GroupRemoteDataSource
import com.costsplit.feature.groups.data.remote.request.group.AddGroupMemberRequest
import com.costsplit.feature.groups.data.remote.request.group.CreateGroupRequest
import com.costsplit.feature.groups.data.remote.response.group.GroupBalancesResponse
import com.costsplit.feature.groups.data.remote.response.group.GroupResponse
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
    dispatchers: AppDispatchers,
) : BaseRemoteDataSource(dispatchers), GroupRemoteDataSource {
    override suspend fun listUserGroups(userId: String): AppResult<List<GroupResponse>> =
        safeApiCall {
            client.get(baseUrl.apiUrl("api/v1/users/$userId/groups")).body()
        }

    override suspend fun createGroup(request: CreateGroupRequest): AppResult<GroupResponse> =
        safeApiCall {
            client.post(baseUrl.apiUrl("api/v1/groups")) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
        }

    override suspend fun getGroup(groupId: String): AppResult<GroupResponse> =
        safeApiCall {
            client.get(baseUrl.apiUrl("api/v1/groups/$groupId")).body()
        }

    override suspend fun addGroupMember(
        groupId: String,
        request: AddGroupMemberRequest,
    ): AppResult<GroupResponse> =
        safeApiCall {
            client.post(baseUrl.apiUrl("api/v1/groups/$groupId/members")) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
        }

    override suspend fun getGroupBalances(groupId: String): AppResult<GroupBalancesResponse> =
        safeApiCall {
            client.get(baseUrl.apiUrl("api/v1/groups/$groupId/balances")).body()
        }
}
