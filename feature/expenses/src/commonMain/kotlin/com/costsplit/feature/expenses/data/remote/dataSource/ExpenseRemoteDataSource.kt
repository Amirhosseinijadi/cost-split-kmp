package com.costsplit.feature.expenses.data.remote.dataSource

import com.costsplit.feature.expenses.data.remote.dto.CreateExpenseRequest
import com.costsplit.feature.expenses.data.remote.dto.ExpenseResponse

interface ExpenseRemoteDataSource {
    suspend fun listGroupExpenses(groupId: String): List<ExpenseResponse>
    suspend fun getExpense(expenseId: String): ExpenseResponse
    suspend fun createExpense(groupId: String, request: CreateExpenseRequest): ExpenseResponse
}