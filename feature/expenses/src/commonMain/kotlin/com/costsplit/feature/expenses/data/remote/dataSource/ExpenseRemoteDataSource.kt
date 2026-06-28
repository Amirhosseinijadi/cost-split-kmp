package com.costsplit.feature.expenses.data.remote.dataSource

import com.costsplit.core.common.result.AppResult
import com.costsplit.feature.expenses.data.remote.request.expense.CreateExpenseRequest
import com.costsplit.feature.expenses.data.remote.response.expense.ExpenseResponse

interface ExpenseRemoteDataSource {
    suspend fun listGroupExpenses(groupId: String): AppResult<List<ExpenseResponse>>
    suspend fun getExpense(expenseId: String): AppResult<ExpenseResponse>
    suspend fun createExpense(groupId: String, request: CreateExpenseRequest): AppResult<ExpenseResponse>
}
