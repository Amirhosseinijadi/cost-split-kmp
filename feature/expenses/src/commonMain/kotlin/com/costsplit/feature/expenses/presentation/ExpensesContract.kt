package com.costsplit.feature.expenses.presentation

import com.costsplit.core.common.mvi.MviEffect
import com.costsplit.core.common.mvi.MviIntent
import com.costsplit.core.common.mvi.MviState
import com.costsplit.feature.expenses.domain.model.Expense
import com.costsplit.feature.expenses.domain.model.NewExpense

sealed interface ExpensesIntent : MviIntent {
    data class Load(val groupId: String) : ExpensesIntent
    data class Retry(val groupId: String) : ExpensesIntent
    data class AddExpense(val expense: NewExpense) : ExpensesIntent
}

data class ExpensesState(
    val expenses: List<Expense> = emptyList(),
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val errorMessage: String? = null,
) : MviState

sealed interface ExpensesEffect : MviEffect {
    data object ExpenseAdded : ExpensesEffect
    data class ShowMessage(val message: String) : ExpensesEffect
}
