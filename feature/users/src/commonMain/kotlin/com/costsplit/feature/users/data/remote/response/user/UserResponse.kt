package com.costsplit.feature.users.data.remote.response.user

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String,
    val displayName: String,
    val email: String,
    val createdAt: String,
)
