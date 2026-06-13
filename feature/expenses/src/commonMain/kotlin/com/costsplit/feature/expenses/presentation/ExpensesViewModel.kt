package com.costsplit.feature.expenses.presentation

import androidx.lifecycle.viewModelScope
import com.costsplit.core.common.mvi.BaseMviViewModel
import com.costsplit.core.common.result.AppError
import com.costsplit.core.common.result.AppResult
import com.costsplit.feature.expenses.domain.usecase.AddExpenseUseCase
import com.costsplit.feature.expenses.domain.usecase.GetExpensesUseCase
import kotlinx.coroutines.launch

class ExpensesViewModel(
    private val getExpenses: GetExpensesUseCase,
    private val addExpense: AddExpenseUseCase,
) : BaseMviViewModel<ExpensesIntent, ExpensesState, ExpensesEffect>(ExpensesState()) {

    override fun handleIntent(intent: ExpensesIntent) {
        when (intent) {
            ExpensesIntent.Load, ExpensesIntent.Retry -> loadExpenses()
            is ExpensesIntent.AddExpense -> saveExpense(intent)
        }
    }

    private fun loadExpenses() = viewModelScope.launch {
        updateState { copy(isLoading = true, errorMessage = null) }
        when (val result = getExpenses()) {
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
        when (val result = addExpense(intent.expense)) {
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

private fun AppError.message(): String = when (this) {
    AppError.Network -> "Unable to connect to the server"
    AppError.Unauthorized -> "Your session has expired"
    is AppError.Server -> "Server error ($code)"
    is AppError.Unknown -> cause?.message ?: "Something went wrong"
}
