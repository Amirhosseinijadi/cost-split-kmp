package com.costsplit.feature.expenses.domain.model

data class NewExpense(
    val groupId: String,
    val description: String,
    val totalAmount: String,
    val currency: String,
    val paidByUserId: String,
    val participantUserIds: Set<String>,
    val category: String = "general",
    val note: String? = null,
    val occurredOn: String? = null,
)
