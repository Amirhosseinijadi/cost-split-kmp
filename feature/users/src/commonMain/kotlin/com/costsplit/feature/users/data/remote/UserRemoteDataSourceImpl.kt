package com.costsplit.feature.users.data.remote

import com.costsplit.core.network.remote.apiUrl
import com.costsplit.feature.users.data.remote.dataSource.UserRemoteDataSource
import com.costsplit.feature.users.data.remote.request.user.CreateUserRequest
import com.costsplit.feature.users.data.remote.response.user.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal class UserRemoteDataSourceImpl(
    private val client: HttpClient,
    private val baseUrl: String,
) : UserRemoteDataSource {
    override suspend fun createUser(request: CreateUserRequest): UserResponse =
        client.post(baseUrl.apiUrl("api/v1/users")) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    override suspend fun listUsers(): List<UserResponse> =
        client.get(baseUrl.apiUrl("api/v1/users")).body()

    override suspend fun getUser(userId: String): UserResponse =
        client.get(baseUrl.apiUrl("api/v1/users/$userId")).body()
}
