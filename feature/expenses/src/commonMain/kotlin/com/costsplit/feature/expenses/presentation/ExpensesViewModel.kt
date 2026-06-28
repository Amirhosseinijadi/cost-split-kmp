package com.costsplit.feature.expenses.presentation

import androidx.lifecycle.viewModelScope
import com.costsplit.core.common.mvi.BaseMviViewModel
import com.costsplit.core.common.result.ApiError
import com.costsplit.core.common.result.AppResult
import com.costsplit.core.common.result.message
import com.costsplit.feature.expenses.domain.usecase.AddExpenseUseCase
import com.costsplit.feature.expenses.domain.usecase.GetExpensesUseCase
import kotlinx.coroutines.launch

class ExpensesViewModel(
    private val getExpenses: GetExpensesUseCase,
    private val addExpense: AddExpenseUseCase,
) : BaseMviViewModel<ExpensesIntent, ExpensesState, ExpensesEffect>(ExpensesState()) {

    override fun handleIntent(intent: ExpensesIntent) {
        when (intent) {
            is ExpensesIntent.Load -> loadExpenses(intent.groupId)
            is ExpensesIntent.Retry -> loadExpenses(intent.groupId)
            is ExpensesIntent.AddExpense -> saveExpense(intent)
        }
    }

    private fun loadExpenses(groupId: String) = viewModelScope.launch {
        updateState { copy(isLoading = true, errorMessage = null) }
        when (val result = getExpenses(groupId)) {
            is AppResult.Success -> updateState {
                copy(expenses = result.value, isLoading = false)
            }

            is AppResult.Failure -> updateState {
                copy(isLoading = false, errorMessage = result.error.message())
            }
        }
    }

    private fun saveExpense(intent: ExpensesIntent.AddExpense) = viewModelScope.launch {
        updateState { copy(isSaving = true, errorMessage = null) }
        addExpense(intent.expense).let { result ->
            when (result) {
                is AppResult.Success -> {
                    updateState {
                        copy(expenses = expenses + result.value, isSaving = false)
                    }
                    emitEffect(ExpensesEffect.ExpenseAdded)
                }

                is AppResult.Failure -> {
                    val message = result.error.message()
                    updateState { copy(isSaving = false, errorMessage = message) }
                    emitEffect(ExpensesEffect.ShowMessage(message))
                }
            }
        }
    }
}
