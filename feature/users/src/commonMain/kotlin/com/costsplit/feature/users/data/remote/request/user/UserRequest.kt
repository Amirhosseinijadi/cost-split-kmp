package com.costsplit.feature.users.data.remote.request.user

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    val displayName: String,
    val email: String,
)
