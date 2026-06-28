package com.costsplit.feature.home.presentation

import com.costsplit.core.common.mvi.MviEffect
import com.costsplit.core.common.mvi.MviIntent
import com.costsplit.core.common.mvi.MviState

sealed interface HomeIntent : MviIntent {
    data object Refresh : HomeIntent
    data object CreateGroupClicked : HomeIntent
    data object ActivityClicked : HomeIntent
}

data class HomeState(
    val title: String = "Cost Split",
    val subtitle: String = "Track shared costs, settle balances, and keep groups clear.",
    val amountYouOwe: String = "$86.40",
    val amountOwedBack: String = "$132.25",
    val oweDetail: String = "Across 3 groups",
    val owedBackDetail: String = "From 5 friends",
    val recentGroups: List<HomeGroupUi> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
) : MviState

data class HomeGroupUi(
    val name: String,
    val members: String,
    val balance: String,
)

sealed interface HomeEffect : MviEffect {
    data object NavigateToGroups : HomeEffect
    data object NavigateToActivity : HomeEffect
}
