package com.costsplit.feature.users.data.remote.dataSource

import com.costsplit.feature.users.data.remote.request.user.CreateUserRequest
import com.costsplit.feature.users.data.remote.response.user.UserResponse

interface UserRemoteDataSource {
    suspend fun createUser(request: CreateUserRequest): UserResponse
    suspend fun listUsers(): List<UserResponse>
    suspend fun getUser(userId: String): UserResponse
}
