package com.costsplit.feature.groups.presentation

import androidx.lifecycle.viewModelScope
import com.costsplit.core.common.mvi.BaseMviViewModel
import com.costsplit.core.common.result.AppResult
import com.costsplit.core.ui.strings.DongiString
import com.costsplit.core.ui.strings.DongiText
import com.costsplit.core.ui.strings.toDongiText
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
                updateState { copy(isLoading = false, errorMessage = result.error.toDongiText()) }
                return@launch
            }
        }
        val activeUser = users.firstOrNull()
        if (activeUser == null) {
            updateState { copy(isLoading = false, errorMessage = DongiText(DongiString.ErrorNoUser)) }
            return@launch
        }

        val groups = when (val result = getUserGroups(activeUser.id)) {
            is AppResult.Success -> result.value
            is AppResult.Failure -> {
                updateState { copy(isLoading = false, errorMessage = result.error.toDongiText()) }
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
            memberCount = members.size,
            balance = userAmount?.formattedMoney(primaryBalance.currency),
            progress = primaryBalance.progressFor(activeUserId),
            settlement = primaryBalance.settlementText(activeUserId),
            currency = primaryBalance?.currency ?: "USD",
            memberBalances = primaryBalance?.members.orEmpty().map {
                MemberBalanceUi(
                    name = it.displayName,
                    amount = it.netAmount.formattedMoney(primaryBalance?.currency ?: "USD"),
                )
            },
        )
    }

    private fun Expense.toUi() = GroupExpenseUi(
        title = description,
        amount = totalAmount.formattedMoney(currency),
        paidBy = paidByDisplayName,
        category = category,
        date = occurredOn,
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

    private fun CurrencyBalance?.settlementText(activeUserId: String): DongiText {
        if (this == null) return DongiText(DongiString.SettlementUnavailable)
        val settlement = suggestedSettlements.firstOrNull()
            ?: return DongiText(DongiString.SettlementComplete)
        val fromName = displayName(settlement.fromUserId)
        val toName = displayName(settlement.toUserId)
        return settlement.toText(activeUserId, fromName, toName, currency)
    }

    private fun CurrencyBalance.displayName(userId: String): String =
        members.firstOrNull { it.userId == userId }?.displayName.orEmpty()

    private fun SuggestedSettlement.toText(
        activeUserId: String,
        fromName: String,
        toName: String,
        currency: String,
    ): DongiText {
        val value = amount.formattedMoney(currency)
        return when {
            fromUserId == activeUserId -> DongiText(
                DongiString.SettlementYouPay,
                listOf(value, toName),
            )
            toUserId == activeUserId -> DongiText(
                DongiString.SettlementPaysYou,
                listOf(fromName, value),
            )
            else -> DongiText(
                DongiString.SettlementMemberPays,
                listOf(fromName, value, toName),
            )
        }
    }

    private fun String.formattedMoney(currency: String): String {
        val sign = if (startsWith("-")) "-" else "+"
        val value = removePrefix("-")
        return if (currency == "USD") "$sign${'$'}$value" else "$sign$value $currency"
    }

}
