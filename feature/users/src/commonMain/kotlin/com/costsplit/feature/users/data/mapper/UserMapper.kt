package com.costsplit.feature.users.data.mapper

import com.costsplit.feature.users.data.remote.request.user.CreateUserRequest
import com.costsplit.feature.users.data.remote.response.user.UserResponse
import com.costsplit.feature.users.domain.model.NewUser
import com.costsplit.feature.users.domain.model.User

internal fun UserResponse.toDomain() = User(
    id = id,
    displayName = displayName,
    email = email,
    createdAt = createdAt,
)

internal fun NewUser.toRequest() = CreateUserRequest(
    displayName = displayName,
    email = email,
)
