package com.costsplit.feature.expenses.data.remote

import com.costsplit.feature.expenses.data.remote.dto.CreateExpenseRequest
import com.costsplit.feature.expenses.data.remote.dto.ExpenseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal class ExpenseApi(
    private val client: HttpClient,
    private val baseUrl: String,
) {
    suspend fun getExpenses(): List<ExpenseDto> = client.get("${baseUrl}api/expenses").body()

    suspend fun addExpense(request: CreateExpenseRequest): ExpenseDto =
        client.post("${baseUrl}api/expenses") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
}

