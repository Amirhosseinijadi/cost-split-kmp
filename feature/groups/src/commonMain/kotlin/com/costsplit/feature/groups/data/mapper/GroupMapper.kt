package com.costsplit.feature.groups.data.mapper

import com.costsplit.feature.groups.data.remote.request.group.CreateGroupRequest
import com.costsplit.feature.groups.data.remote.response.group.CurrencyBalanceResponse
import com.costsplit.feature.groups.data.remote.response.group.GroupBalancesResponse
import com.costsplit.feature.groups.data.remote.response.group.GroupMemberResponse
import com.costsplit.feature.groups.data.remote.response.group.GroupResponse
import com.costsplit.feature.groups.data.remote.response.group.MemberBalanceResponse
import com.costsplit.feature.groups.data.remote.response.group.SuggestedSettlementResponse
import com.costsplit.feature.groups.domain.model.CurrencyBalance
import com.costsplit.feature.groups.domain.model.Group
import com.costsplit.feature.groups.domain.model.GroupBalances
import com.costsplit.feature.groups.domain.model.GroupMember
import com.costsplit.feature.groups.domain.model.MemberBalance
import com.costsplit.feature.groups.domain.model.NewGroup
import com.costsplit.feature.groups.domain.model.SuggestedSettlement

internal fun GroupResponse.toDomain() = Group(
    id = id,
    name = name,
    ownerUserId = ownerUserId,
    icon = icon,
    color = color,
    members = members.map { it.toDomain() },
    createdAt = createdAt,
)

private fun GroupMemberResponse.toDomain() = GroupMember(
    userId = userId,
    displayName = displayName,
)

internal fun NewGroup.toRequest() = CreateGroupRequest(
    name = name,
    ownerUserId = ownerUserId,
    memberUserIds = memberUserIds,
    icon = icon,
    color = color,
)

internal fun GroupBalancesResponse.toDomain() = GroupBalances(
    groupId = groupId,
    balances = balances.map { it.toDomain() },
)

private fun CurrencyBalanceResponse.toDomain() = CurrencyBalance(
    currency = currency,
    members = members.map { it.toDomain() },
    suggestedSettlements = suggestedSettlements.map { it.toDomain() },
)

private fun MemberBalanceResponse.toDomain() = MemberBalance(
    userId = userId,
    displayName = displayName,
    netAmount = netAmount,
)

private fun SuggestedSettlementResponse.toDomain() = SuggestedSettlement(
    fromUserId = fromUserId,
    toUserId = toUserId,
    amount = amount,
)
