package com.costsplit.feature.expenses.domain.model

data class NewExpense(
    val description: String,
    val amountInMinorUnits: Long,
    val currency: String,
    val paidByMemberId: String,
)

