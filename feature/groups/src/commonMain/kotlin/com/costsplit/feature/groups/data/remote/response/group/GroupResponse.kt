package com.costsplit.feature.groups.data.remote.response.group

import kotlinx.serialization.Serializable

@Serializable
data class GroupMemberResponse(
    val userId: String,
    val displayName: String,
    val email: String,
    val joinedAt: String,
)

@Serializable
data class GroupResponse(
    val id: String,
    val name: String,
    val ownerUserId: String,
    val members: List<GroupMemberResponse>,
    val createdAt: String,
)
