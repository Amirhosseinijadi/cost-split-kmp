package com.costsplit.feature.expenses.data.mapper

import com.costsplit.feature.expenses.data.remote.dto.CreateExpenseRequest
import com.costsplit.feature.expenses.data.remote.dto.ExpenseDto
import com.costsplit.feature.expenses.domain.model.Expense
import com.costsplit.feature.expenses.domain.model.NewExpense

internal fun ExpenseDto.toDomain() = Expense(id, description, amountInMinorUnits, currency, paidByMemberId)

internal fun NewExpense.toRequest() = CreateExpenseRequest(description, amountInMinorUnits, currency, paidByMemberId)

