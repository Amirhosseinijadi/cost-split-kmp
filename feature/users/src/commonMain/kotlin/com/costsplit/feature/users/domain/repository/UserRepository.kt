package com.costsplit.feature.users.domain.repository

import com.costsplit.core.common.result.AppResult
import com.costsplit.feature.users.domain.model.NewUser
import com.costsplit.feature.users.domain.model.User

interface UserRepository {
    suspend fun createUser(user: NewUser): AppResult<User>
    suspend fun listUsers(): AppResult<List<User>>
    suspend fun getUser(userId: String): AppResult<User>
}
