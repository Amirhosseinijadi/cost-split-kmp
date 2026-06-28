package com.costsplit.feature.expenses.data.remote

import com.costsplit.core.common.coroutine.AppDispatchers
import com.costsplit.core.common.result.AppResult
import com.costsplit.core.network.remote.BaseRemoteDataSource
import com.costsplit.core.network.remote.apiUrl
import com.costsplit.feature.expenses.data.remote.dataSource.ExpenseRemoteDataSource
import com.costsplit.feature.expenses.data.remote.request.expense.CreateExpenseRequest
import com.costsplit.feature.expenses.data.remote.response.expense.ExpenseResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal class ExpenseRemoteDataSourceImpl(
    private val client: HttpClient,
    private val baseUrl: String,
    dispatchers: AppDispatchers,
) : BaseRemoteDataSource(dispatchers), ExpenseRemoteDataSource {
    override suspend fun listGroupExpenses(groupId: String): AppResult<List<ExpenseResponse>> =
        safeApiCall {
            client.get(baseUrl.apiUrl("api/v1/groups/$groupId/expenses")).body()
        }

    override suspend fun getExpense(expenseId: String): AppResult<ExpenseResponse> =
        safeApiCall {
            client.get(baseUrl.apiUrl("api/v1/expenses/$expenseId")).body()
        }

    override suspend fun createExpense(
        groupId: String,
        request: CreateExpenseRequest,
    ): AppResult<ExpenseResponse> =
        safeApiCall {
            client.post(baseUrl.apiUrl("api/v1/groups/$groupId/expenses")) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
        }
}
