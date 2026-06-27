package com.costsplit.feature.expenses.domain.repository

import com.costsplit.core.common.result.AppResult
import com.costsplit.feature.expenses.domain.model.Expense
import com.costsplit.feature.expenses.domain.model.NewExpense

interface ExpenseRepository {
    suspend fun getExpenses(groupId: String): AppResult<List<Expense>>
    suspend fun getExpense(expenseId: String): AppResult<Expense>
    suspend fun addExpense(expense: NewExpense): AppResult<Expense>
}
