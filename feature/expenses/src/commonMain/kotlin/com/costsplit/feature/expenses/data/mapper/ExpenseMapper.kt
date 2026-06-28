package com.costsplit.feature.expenses.data.mapper

import com.costsplit.feature.expenses.data.remote.request.expense.CreateExpenseRequest
import com.costsplit.feature.expenses.data.remote.response.expense.ExpenseResponse
import com.costsplit.feature.expenses.data.remote.response.expense.ExpenseShareResponse
import com.costsplit.feature.expenses.domain.model.Expense
import com.costsplit.feature.expenses.domain.model.ExpenseShare
import com.costsplit.feature.expenses.domain.model.NewExpense

internal fun ExpenseResponse.toDomain() = Expense(
    id = id,
    groupId = groupId,
    description = description,
    totalAmount = totalAmount,
    currency = currency,
    paidByUserId = paidByUserId,
    paidByDisplayName = paidByDisplayName,
    splitType = splitType,
    shares = shares.map { it.toDomain() },
    createdAt = createdAt,
)

private fun ExpenseShareResponse.toDomain() = ExpenseShare(
    userId = userId,
    displayName = displayName,
    amountOwed = amountOwed,
)

internal fun NewExpense.toRequest() = CreateExpenseRequest(
    description = description,
    totalAmount = totalAmount,
    currency = currency,
    paidByUserId = paidByUserId,
    participantUserIds = participantUserIds,
)
