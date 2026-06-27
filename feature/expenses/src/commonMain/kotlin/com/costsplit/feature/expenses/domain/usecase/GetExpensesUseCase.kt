package com.costsplit.feature.expenses.domain.usecase

import com.costsplit.feature.expenses.domain.repository.ExpenseRepository

class GetExpensesUseCase(private val repository: ExpenseRepository) {
    suspend operator fun invoke(groupId: String) = repository.getExpenses(groupId)
}
