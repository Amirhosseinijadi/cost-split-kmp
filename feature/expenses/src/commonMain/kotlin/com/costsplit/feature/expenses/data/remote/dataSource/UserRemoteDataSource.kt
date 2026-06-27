package com.costsplit.feature.expenses.data.remote.dataSource

import com.costsplit.feature.expenses.data.remote.dto.CreateUserRequest
import com.costsplit.feature.expenses.data.remote.dto.UserResponse

interface UserRemoteDataSource {
    suspend fun createUser(request: CreateUserRequest): UserResponse
    suspend fun listUsers(): List<UserResponse>
    suspend fun getUser(userId: String): UserResponse
}