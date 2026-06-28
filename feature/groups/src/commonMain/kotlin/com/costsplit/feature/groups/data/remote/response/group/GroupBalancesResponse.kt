package com.costsplit.feature.groups.data.remote.response.group

import com.costsplit.core.network.serialization.DecimalStringSerializer
import kotlinx.serialization.Serializable

@Serializable
data class MemberBalanceResponse(
    val userId: String,
    val displayName: String,
    @Serializable(with = DecimalStringSerializer::class)
    val netAmount: String,
)

@Serializable
data class SuggestedSettlementResponse(
    val fromUserId: String,
    val toUserId: String,
    @Serializable(with = DecimalStringSerializer::class)
    val amount: String,
)

@Serializable
data class CurrencyBalanceResponse(
    val currency: String,
    val members: List<MemberBalanceResponse>,
    val suggestedSettlements: List<SuggestedSettlementResponse>,
)

@Serializable
data class GroupBalancesResponse(
    val groupId: String,
    val balances: List<CurrencyBalanceResponse>,
)
