package com.costsplit.feature.groups.data.remote.request.group

import kotlinx.serialization.Serializable

@Serializable
data class CreateGroupRequest(
    val name: String,
    val ownerUserId: String,
    val memberUserIds: Set<String> = emptySet(),
)

@Serializable
data class AddGroupMemberRequest(
    val userId: String,
)
