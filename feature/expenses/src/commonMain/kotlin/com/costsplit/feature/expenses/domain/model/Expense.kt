package com.costsplit.feature.expenses.domain.model

data class Expense(
    val id: String,
    val description: String,
    val amountInMinorUnits: Long,
    val currency: String,
    val paidByMemberId: String,
)

