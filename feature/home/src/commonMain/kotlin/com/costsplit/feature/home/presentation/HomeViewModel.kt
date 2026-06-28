package com.costsplit.feature.home.presentation

import androidx.lifecycle.viewModelScope
import com.costsplit.core.common.mvi.BaseMviViewModel
import com.costsplit.core.common.result.AppResult
import com.costsplit.core.common.result.message
import com.costsplit.feature.groups.domain.model.Group
import com.costsplit.feature.groups.domain.model.GroupBalances
import com.costsplit.feature.groups.domain.usecase.GetGroupBalancesUseCase
import com.costsplit.feature.groups.domain.usecase.GetUserGroupsUseCase
import com.costsplit.feature.users.domain.usecase.GetUsersUseCase
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getUsers: GetUsersUseCase,
    private val getUserGroups: GetUserGroupsUseCase,
    private val getGroupBalances: GetGroupBalancesUseCase,
) : BaseMviViewModel<HomeIntent, HomeState, HomeEffect>(HomeState()) {
    init {
        loadHome()
    }

    override fun handleIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.Refresh -> loadHome()
            HomeIntent.CreateGroupClicked -> viewModelScope.launch {
                emitEffect(HomeEffect.NavigateToGroups)
            }

            HomeIntent.ActivityClicked -> viewModelScope.launch {
                emitEffect(HomeEffect.NavigateToActivity)
            }
        }
    }

    private fun loadHome() = viewModelScope.launch {
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
        val balances = groups.associateWith { group ->
            when (val result = getGroupBalances(group.id)) {
                is AppResult.Success -> result.value
                is AppResult.Failure -> null
            }
        }

        val totals = balances.values.filterNotNull().summedFor(activeUser.id)
        updateState {
            copy(
                amountYouOwe = totals.owe.formattedMoney(totals.currency),
                amountOwedBack = totals.owedBack.formattedMoney(totals.currency),
                oweDetail = groups.size.groupCountLabel(),
                owedBackDetail = users.size.friendCountLabel(),
                recentGroups = groups.take(3).map { group ->
                    group.toHomeUi(balances[group], activeUser.id)
                },
                isLoading = false,
                errorMessage = null,
            )
        }
    }

    private fun Group.toHomeUi(balances: GroupBalances?, activeUserId: String) = HomeGroupUi(
        name = name,
        members = "${members.size} members",
        balance = balances?.primaryUserBalance(activeUserId) ?: "No balance",
    )

    private fun GroupBalances.primaryUserBalance(activeUserId: String): String? {
        val balance = balances.firstOrNull() ?: return null
        val amount = balance.members.firstOrNull { it.userId == activeUserId }?.netAmount ?: return null
        return amount.formattedMoney(balance.currency)
    }

    private fun List<GroupBalances>.summedFor(activeUserId: String): BalanceTotals {
        var owe = 0.0
        var owedBack = 0.0
        var currency = "USD"
        forEach { groupBalances ->
            val balance = groupBalances.balances.firstOrNull() ?: return@forEach
            currency = balance.currency
            val amount = balance.members.firstOrNull { it.userId == activeUserId }
                ?.netAmount
                ?.toDoubleOrNull()
                ?: return@forEach
            if (amount < 0) owe += abs(amount) else owedBack += amount
        }
        return BalanceTotals(owe = owe, owedBack = owedBack, currency = currency)
    }

    private fun Double.formattedMoney(currency: String) = ((this * 100).roundToInt() / 100.0)
        .toString()
        .formattedMoney(currency)

    private fun String.formattedMoney(currency: String): String {
        val value = removePrefix("-")
        return if (currency == "USD") "${'$'}$value" else "$value $currency"
    }

    private fun Int.groupCountLabel() = "Across $this groups"

    private fun Int.friendCountLabel() = "From $this friends"

    private data class BalanceTotals(
        val owe: Double,
        val owedBack: Double,
        val currency: String,
    )
}
