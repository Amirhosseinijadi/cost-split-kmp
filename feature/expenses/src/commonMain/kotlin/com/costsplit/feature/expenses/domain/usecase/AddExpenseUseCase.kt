package com.costsplit.feature.expenses.domain.usecase

import com.costsplit.core.common.result.ApiError
import com.costsplit.core.common.result.AppResult
import com.costsplit.feature.expenses.domain.model.NewExpense
import com.costsplit.feature.expenses.domain.repository.ExpenseRepository

class AddExpenseUseCase(private val repository: ExpenseRepository) {
    suspend operator fun invoke(expense: NewExpense): AppResult<com.costsplit.feature.expenses.domain.model.Expense> {
        val amount = expense.totalAmount.toDoubleOrNull()
        if (
            expense.groupId.isBlank() ||
            expense.description.isBlank() ||
            amount == null ||
            amount <= 0.0 ||
            expense.currency.length != 3 ||
            expense.paidByUserId.isBlank() ||
            expense.participantUserIds.isEmpty()
        ) {
            return AppResult.Failure(ApiError.Unknown(IllegalArgumentException("Invalid expense")))
        }
        return repository.addExpense(expense)
    }
}
