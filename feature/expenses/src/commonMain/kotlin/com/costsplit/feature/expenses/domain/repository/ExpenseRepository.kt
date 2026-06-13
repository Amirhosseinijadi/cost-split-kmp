package com.costsplit.feature.expenses.domain.repository

import com.costsplit.core.common.result.AppResult
import com.costsplit.feature.expenses.domain.model.Expense
import com.costsplit.feature.expenses.domain.model.NewExpense

interface ExpenseRepository {
    suspend fun getExpenses(): AppResult<List<Expense>>
    suspend fun addExpense(expense: NewExpense): AppResult<Expense>
}

