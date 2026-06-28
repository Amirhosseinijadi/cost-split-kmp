package com.costsplit.feature.groups.domain.model

data class GroupBalances(
    val groupId: String,
    val balances: List<CurrencyBalance>,
)

data class CurrencyBalance(
    val currency: String,
    val members: List<MemberBalance>,
    val suggestedSettlements: List<SuggestedSettlement>,
)

data class MemberBalance(
    val userId: String,
    val displayName: String,
    val netAmount: String,
)

data class SuggestedSettlement(
    val fromUserId: String,
    val toUserId: String,
    val amount: String,
)
