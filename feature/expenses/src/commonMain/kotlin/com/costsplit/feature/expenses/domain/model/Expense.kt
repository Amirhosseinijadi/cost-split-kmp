package com.costsplit.feature.expenses.domain.model

data class Expense(
    val id: String,
    val groupId: String,
    val description: String,
    val totalAmount: String,
    val currency: String,
    val paidByUserId: String,
    val paidByDisplayName: String,
    val splitType: String,
    val category: String,
    val note: String?,
    val occurredOn: String,
    val shares: List<ExpenseShare>,
    val createdAt: String,
)

data class ExpenseShare(
    val userId: String,
    val displayName: String,
    val amountOwed: String,
)
