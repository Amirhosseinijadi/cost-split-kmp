package com.costsplit.feature.groups.domain.model

data class NewGroup(
    val name: String,
    val ownerUserId: String,
    val memberUserIds: Set<String> = emptySet(),
    val icon: String? = null,
    val color: String? = null,
)
