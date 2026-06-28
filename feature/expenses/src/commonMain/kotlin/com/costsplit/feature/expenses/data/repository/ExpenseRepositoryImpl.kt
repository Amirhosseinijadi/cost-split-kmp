package com.costsplit.feature.expenses.data.repository

import com.costsplit.core.common.result.AppResult
import com.costsplit.core.common.result.map
import com.costsplit.feature.expenses.data.mapper.toDomain
import com.costsplit.feature.expenses.data.mapper.toRequest
import com.costsplit.feature.expenses.data.remote.dataSource.ExpenseRemoteDataSource
import com.costsplit.feature.expenses.domain.model.Expense
import com.costsplit.feature.expenses.domain.model.NewExpense
import com.costsplit.feature.expenses.domain.repository.ExpenseRepository

internal class ExpenseRepositoryImpl(private val api: ExpenseRemoteDataSource) :
    ExpenseRepository {
    override suspend fun getExpenses(groupId: String): AppResult<List<Expense>> =
        api.listGroupExpenses(groupId).map { expenses ->
            expenses.map { it.toDomain() }
        }

    override suspend fun getExpense(expenseId: String): AppResult<Expense> =
        api.getExpense(expenseId).map { it.toDomain() }

    override suspend fun addExpense(expense: NewExpense): AppResult<Expense> =
        api.createExpense(expense.groupId, expense.toRequest()).map { it.toDomain() }
}
