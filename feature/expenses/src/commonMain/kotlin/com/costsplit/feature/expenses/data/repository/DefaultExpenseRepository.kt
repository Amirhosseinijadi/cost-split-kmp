package com.costsplit.feature.expenses.data.repository

import com.costsplit.core.common.result.AppError
import com.costsplit.core.common.result.AppResult
import com.costsplit.feature.expenses.data.mapper.toDomain
import com.costsplit.feature.expenses.data.mapper.toRequest
import com.costsplit.feature.expenses.data.remote.ExpenseApi
import com.costsplit.feature.expenses.domain.model.Expense
import com.costsplit.feature.expenses.domain.model.NewExpense
import com.costsplit.feature.expenses.domain.repository.ExpenseRepository
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException

internal class DefaultExpenseRepository(private val api: ExpenseApi) : ExpenseRepository {
    override suspend fun getExpenses(): AppResult<List<Expense>> = apiCall {
        api.getExpenses().map { it.toDomain() }
    }

    override suspend fun addExpense(expense: NewExpense): AppResult<Expense> = apiCall {
        api.addExpense(expense.toRequest()).toDomain()
    }

    private suspend fun <T> apiCall(block: suspend () -> T): AppResult<T> = try {
        AppResult.Success(block())
    } catch (error: ClientRequestException) {
        if (error.response.status.value == 401) AppResult.Failure(AppError.Unauthorized)
        else AppResult.Failure(AppError.Server(error.response.status.value))
    } catch (error: ServerResponseException) {
        AppResult.Failure(AppError.Server(error.response.status.value))
    } catch (error: Throwable) {
        AppResult.Failure(AppError.Network)
    }
}

