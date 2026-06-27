package com.costsplit.feature.expenses.data.repository

import com.costsplit.core.common.result.AppResult
import com.costsplit.core.network.remote.BaseRemoteDataSource
import com.costsplit.feature.expenses.data.mapper.toDomain
import com.costsplit.feature.expenses.data.mapper.toRequest
import com.costsplit.feature.expenses.data.remote.ExpenseRemoteDataSourceImpl
import com.costsplit.feature.expenses.domain.model.Expense
import com.costsplit.feature.expenses.domain.model.NewExpense
import com.costsplit.feature.expenses.domain.repository.ExpenseRepository

internal class ExpenseRepositoryImpl(private val api: ExpenseRemoteDataSourceImpl) :
    BaseRemoteDataSource(),
    ExpenseRepository {
    override suspend fun getExpenses(groupId: String): AppResult<List<Expense>> = apiCall {
        api.listGroupExpenses(groupId).map { it.toDomain() }
    }

    override suspend fun getExpense(expenseId: String): AppResult<Expense> = apiCall {
        api.getExpense(expenseId).toDomain()
    }

    override suspend fun addExpense(expense: NewExpense): AppResult<Expense> = apiCall {
        api.createExpense(expense.groupId, expense.toRequest()).toDomain()
    }
}
