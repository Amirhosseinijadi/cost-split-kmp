package com.costsplit.feature.groups.domain.model

data class Group(
    val id: String,
    val name: String,
    val ownerUserId: String,
    val icon: String?,
    val color: String?,
    val members: List<GroupMember>,
    val createdAt: String,
)

data class GroupMember(
    val userId: String,
    val displayName: String,
)
