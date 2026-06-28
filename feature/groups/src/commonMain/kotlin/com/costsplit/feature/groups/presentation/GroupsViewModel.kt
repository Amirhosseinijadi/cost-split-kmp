package com.costsplit.feature.groups.presentation

import androidx.lifecycle.viewModelScope
import com.costsplit.core.common.mvi.BaseMviViewModel
import com.costsplit.core.common.result.AppResult
import com.costsplit.core.common.result.message
import com.costsplit.feature.expenses.domain.model.Expense
import com.costsplit.feature.expenses.domain.usecase.GetExpensesUseCase
import com.costsplit.feature.groups.domain.model.CurrencyBalance
import com.costsplit.feature.groups.domain.model.Group
import com.costsplit.feature.groups.domain.model.GroupBalances
import com.costsplit.feature.groups.domain.model.SuggestedSettlement
import com.costsplit.feature.groups.domain.usecase.GetGroupBalancesUseCase
import com.costsplit.feature.groups.domain.usecase.GetUserGroupsUseCase
import com.costsplit.feature.users.domain.usecase.GetUsersUseCase
import kotlin.math.abs
import kotlin.math.max
import kotlinx.coroutines.launch

class GroupsViewModel(
    private val getUsers: GetUsersUseCase,
    private val getUserGroups: GetUserGroupsUseCase,
    private val getGroupBalances: GetGroupBalancesUseCase,
    private val getExpenses: GetExpensesUseCase,
) : BaseMviViewModel<GroupsIntent, GroupsState, GroupsEffect>(GroupsState()) {
    init {
        loadGroups()
    }

    override fun handleIntent(intent: GroupsIntent) {
        when (intent) {
            GroupsIntent.Refresh -> loadGroups()
            GroupsIntent.AddGroupClicked -> Unit
            is GroupsIntent.GroupClicked -> viewModelScope.launch {
                emitEffect(GroupsEffect.NavigateToGroupDetails(intent.groupId))
            }
        }
    }

    private fun loadGroups() = viewModelScope.launch {
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

        val balanceByGroup = groups.associate { group ->
            val balance = when (val result = getGroupBalances(group.id)) {
                is AppResult.Success -> result.value
                is AppResult.Failure -> null
            }
            group.id to balance
        }
        val expensesByGroup = groups.associate { group ->
            val expenses = when (val result = getExpenses(group.id)) {
                is AppResult.Success -> result.value.map { it.toUi() }
                is AppResult.Failure -> emptyList()
            }
            group.id to expenses
        }

        updateState {
            copy(
                groups = groups.map { it.toUi(balanceByGroup[it.id], activeUser.id) },
                expenses = expensesByGroup,
                isLoading = false,
                errorMessage = null,
            )
        }
    }

    private fun Group.toUi(balances: GroupBalances?, activeUserId: String): GroupUi {
        val primaryBalance = balances?.balances?.firstOrNull()
        val userAmount = primaryBalance?.members
            ?.firstOrNull { it.userId == activeUserId }
            ?.netAmount
        return GroupUi(
            id = id,
            name = name,
            members = "${members.size} members",
            balance = userAmount?.formattedMoney(primaryBalance.currency) ?: "No balance",
            progress = primaryBalance.progressFor(activeUserId),
            settlement = primaryBalance.settlementText(activeUserId),
        )
    }

    private fun Expense.toUi() = GroupExpenseUi(
        title = description,
        amount = totalAmount.formattedMoney(currency),
    )

    private fun CurrencyBalance?.progressFor(activeUserId: String): Float {
        if (this == null) return 0f
        val userAmount = members.firstOrNull { it.userId == activeUserId }
            ?.netAmount
            ?.toDoubleOrNull()
            ?: return 1f
        val maxAmount = members.maxOfOrNull { abs(it.netAmount.toDoubleOrNull() ?: 0.0) } ?: return 1f
        if (maxAmount == 0.0) return 1f
        return (abs(userAmount) / max(maxAmount, 1.0)).toFloat().coerceIn(0f, 1f)
    }

    private fun CurrencyBalance?.settlementText(activeUserId: String): String {
        if (this == null) return "No settlement available"
        val settlement = suggestedSettlements.firstOrNull() ?: return "All settled"
        val fromName = displayName(settlement.fromUserId)
        val toName = displayName(settlement.toUserId)
        return settlement.toText(activeUserId, fromName, toName, currency)
    }

    private fun CurrencyBalance.displayName(userId: String): String =
        members.firstOrNull { it.userId == userId }?.displayName ?: "Member"

    private fun SuggestedSettlement.toText(
        activeUserId: String,
        fromName: String,
        toName: String,
        currency: String,
    ): String {
        val value = amount.formattedMoney(currency)
        return when {
            fromUserId == activeUserId -> "You pay $toName $value"
            toUserId == activeUserId -> "$fromName pays you $value"
            else -> "$fromName pays $toName $value"
        }
    }

    private fun String.formattedMoney(currency: String): String {
        val value = removePrefix("-")
        return if (currency == "USD") "${'$'}$value" else "$value $currency"
    }
}
