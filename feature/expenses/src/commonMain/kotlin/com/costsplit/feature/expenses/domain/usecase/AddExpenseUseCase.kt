package com.costsplit.feature.expenses.domain.usecase

import com.costsplit.core.common.result.AppError
import com.costsplit.core.common.result.AppResult
import com.costsplit.feature.expenses.domain.model.NewExpense
import com.costsplit.feature.expenses.domain.repository.ExpenseRepository

class AddExpenseUseCase(private val repository: ExpenseRepository) {
    suspend operator fun invoke(expense: NewExpense): AppResult<com.costsplit.feature.expenses.domain.model.Expense> {
        if (expense.description.isBlank() || expense.amountInMinorUnits <= 0) {
            return AppResult.Failure(AppError.Unknown(IllegalArgumentException("Invalid expense")))
        }
        return repository.addExpense(expense)
    }
}

