package com.costsplit.feature.expenses.data.remote

import com.costsplit.feature.expenses.data.remote.dataSource.ExpenseRemoteDataSource
import com.costsplit.feature.expenses.data.remote.dto.CreateExpenseRequest
import com.costsplit.feature.expenses.data.remote.dto.ExpenseResponse
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
) : ExpenseRemoteDataSource {
    override suspend fun listGroupExpenses(groupId: String): List<ExpenseResponse> =
        client.get(baseUrl.apiUrl("api/v1/groups/$groupId/expenses")).body()

    override suspend fun getExpense(expenseId: String): ExpenseResponse =
        client.get(baseUrl.apiUrl("api/v1/expenses/$expenseId")).body()

    override suspend fun createExpense(
        groupId: String,
        request: CreateExpenseRequest
    ): ExpenseResponse =
        client.post(baseUrl.apiUrl("api/v1/groups/$groupId/expenses")) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
}
