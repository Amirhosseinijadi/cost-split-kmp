package com.costsplit.feature.users.data.remote.dataSource

import com.costsplit.core.common.result.AppResult
import com.costsplit.feature.users.data.remote.request.user.CreateUserRequest
import com.costsplit.feature.users.data.remote.response.user.UserResponse

interface UserRemoteDataSource {
    suspend fun createUser(request: CreateUserRequest): AppResult<UserResponse>
    suspend fun listUsers(): AppResult<List<UserResponse>>
    suspend fun getUser(userId: String): AppResult<UserResponse>
}
