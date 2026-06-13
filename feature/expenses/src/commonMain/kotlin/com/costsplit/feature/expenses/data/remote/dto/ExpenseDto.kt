package com.costsplit.feature.expenses.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ExpenseDto(
    val id: String,
    val description: String,
    val amountInMinorUnits: Long,
    val currency: String,
    val paidByMemberId: String,
)

@Serializable
data class CreateExpenseRequest(
    val description: String,
    val amountInMinorUnits: Long,
    val currency: String,
    val paidByMemberId: String,
)

