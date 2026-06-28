package com.costsplit.feature.expenses.data.remote.response.expense

import com.costsplit.core.network.serialization.DecimalStringSerializer
import kotlinx.serialization.Serializable

@Serializable
data class ExpenseShareResponse(
    val userId: String,
    val displayName: String,
    @Serializable(with = DecimalStringSerializer::class)
    val amountOwed: String,
)

@Serializable
data class ExpenseResponse(
    val id: String,
    val groupId: String,
    val description: String,
    @Serializable(with = DecimalStringSerializer::class)
    val totalAmount: String,
    val currency: String,
    val paidByUserId: String,
    val paidByDisplayName: String,
    val splitType: String,
    val category: String = "general",
    val note: String? = null,
    val occurredOn: String,
    val shares: List<ExpenseShareResponse>,
    val createdAt: String,
)
