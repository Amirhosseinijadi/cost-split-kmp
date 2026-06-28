package com.costsplit.feature.users.data.repository

import com.costsplit.core.common.result.AppResult
import com.costsplit.core.common.result.map
import com.costsplit.feature.users.data.mapper.toDomain
import com.costsplit.feature.users.data.mapper.toRequest
import com.costsplit.feature.users.data.remote.dataSource.UserRemoteDataSource
import com.costsplit.feature.users.domain.model.NewUser
import com.costsplit.feature.users.domain.model.User
import com.costsplit.feature.users.domain.repository.UserRepository

internal class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
) : UserRepository {
    override suspend fun createUser(user: NewUser): AppResult<User> =
        remoteDataSource.createUser(user.toRequest()).map { it.toDomain() }

    override suspend fun listUsers(): AppResult<List<User>> =
        remoteDataSource.listUsers().map { users -> users.map { it.toDomain() } }

    override suspend fun getUser(userId: String): AppResult<User> =
        remoteDataSource.getUser(userId).map { it.toDomain() }
}
