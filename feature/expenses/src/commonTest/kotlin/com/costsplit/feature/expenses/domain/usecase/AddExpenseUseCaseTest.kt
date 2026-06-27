package com.costsplit.feature.expenses.domain.usecase

import com.costsplit.core.common.result.AppResult
import com.costsplit.feature.expenses.domain.model.Expense
import com.costsplit.feature.expenses.domain.model.NewExpense
import com.costsplit.feature.expenses.domain.repository.ExpenseRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertIs

class AddExpenseUseCaseTest {
    @Test
    fun rejectsInvalidExpenseBeforeRepositoryCall() = runTest {
        val result = AddExpenseUseCase(FakeExpenseRepository())(
            NewExpense(
                groupId = "group-1",
                description = "",
                totalAmount = "0",
                currency = "USD",
                paidByUserId = "user-1",
                participantUserIds = setOf("user-1"),
            )
        )

        assertIs<AppResult.Failure>(result)
    }
}

private class FakeExpenseRepository : ExpenseRepository {
    override suspend fun getExpenses(groupId: String): AppResult<List<Expense>> = AppResult.Success(emptyList())
    override suspend fun getExpense(expenseId: String): AppResult<Expense> = error("Must not be called")
    override suspend fun addExpense(expense: NewExpense): AppResult<Expense> = error("Must not be called")
}
