package com.costsplit.feature.activity

import androidx.lifecycle.viewModelScope
import com.costsplit.core.common.mvi.BaseMviViewModel
import com.costsplit.core.common.result.AppResult
import com.costsplit.core.common.result.message
import com.costsplit.feature.expenses.domain.model.Expense
import com.costsplit.feature.expenses.domain.usecase.GetExpensesUseCase
import com.costsplit.feature.groups.domain.model.Group
import com.costsplit.feature.groups.domain.usecase.GetUserGroupsUseCase
import com.costsplit.feature.users.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.launch

class ActivityViewModel(
    private val getUsers: GetUsersUseCase,
    private val getUserGroups: GetUserGroupsUseCase,
    private val getExpenses: GetExpensesUseCase,
) : BaseMviViewModel<ActivityIntent, ActivityState, ActivityEffect>(ActivityState()) {
    init {
        loadActivity()
    }

    override fun handleIntent(intent: ActivityIntent) {
        when (intent) {
            ActivityIntent.Refresh -> loadActivity()
        }
    }

    private fun loadActivity() = viewModelScope.launch {
        updateState { copy(isLoading = true, errorMessage = null) }

        val users = when (val result = getUsers()) {
            is AppResult.Success -> result.value
            is AppResult.Failure -> {
                updateState { copy(isLoading = false, errorMessage = result.error.message()) }
                return@launch
            }
        }
        val activeUser = users.firstOrNull()
        if (activeUser == null) {
            updateState { copy(isLoading = false, errorMessage = "No users found.") }
            return@launch
        }

        val groups = when (val result = getUserGroups(activeUser.id)) {
            is AppResult.Success -> result.value
            is AppResult.Failure -> {
                updateState { copy(isLoading = false, errorMessage = result.error.message()) }
                return@launch
            }
        }

        val activities = groups
            .flatMap { group -> group.expenseActivities() }
            .sortedByDescending { it.sortKey }
            .map { it.activity }

        updateState {
            copy(
                activities = activities,
                isLoading = false,
                errorMessage = null,
            )
        }
    }

    private suspend fun Group.expenseActivities(): List<ActivityItem> {
        val expenses = when (val result = getExpenses(id)) {
            is AppResult.Success -> result.value
            is AppResult.Failure -> emptyList()
        }
        return expenses.map { expense ->
            ActivityItem(
                sortKey = expense.createdAt,
                activity = expense.toActivity(groupName = name),
            )
        }
    }

    private fun Expense.toActivity(groupName: String) = ActivityUi(
        title = "$paidByDisplayName added $description",
        group = groupName,
        amount = totalAmount.formattedMoney(currency),
        date = occurredOn.ifBlank { createdAt.take(10) },
    )

    private fun String.formattedMoney(currency: String): String {
        val value = removePrefix("-")
        return if (currency == "USD") "${'$'}$value" else "$value $currency"
    }

    private data class ActivityItem(
        val sortKey: String,
        val activity: ActivityUi,
    )
}
