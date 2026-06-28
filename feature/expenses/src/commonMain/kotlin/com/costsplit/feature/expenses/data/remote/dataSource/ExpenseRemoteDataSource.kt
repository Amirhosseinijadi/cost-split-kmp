package com.costsplit.feature.expenses.data.remote.dataSource

import com.costsplit.feature.expenses.data.remote.request.expense.CreateExpenseRequest
import com.costsplit.feature.expenses.data.remote.response.expense.ExpenseResponse

interface ExpenseRemoteDataSource {
    suspend fun listGroupExpenses(groupId: String): List<ExpenseResponse>
    suspend fun getExpense(expenseId: String): ExpenseResponse
    suspend fun createExpense(groupId: String, request: CreateExpenseRequest): ExpenseResponse
}
