package com.costsplit.feature.expenses.data.remote.request.expense

import com.costsplit.core.network.serialization.DecimalStringSerializer
import kotlinx.serialization.Serializable

@Serializable
data class CreateExpenseRequest(
    val description: String,
    @Serializable(with = DecimalStringSerializer::class)
    val totalAmount: String,
    val currency: String,
    val paidByUserId: String,
    val participantUserIds: Set<String>,
    val category: String = "general",
    val note: String? = null,
    val occurredOn: String? = null,
)
