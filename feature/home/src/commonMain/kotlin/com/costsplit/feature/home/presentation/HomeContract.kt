package com.costsplit.feature.home.presentation

import com.costsplit.core.common.mvi.MviEffect
import com.costsplit.core.common.mvi.MviIntent
import com.costsplit.core.common.mvi.MviState
import com.costsplit.core.ui.strings.DongiText

sealed interface HomeIntent : MviIntent {
    data object Refresh : HomeIntent
    data object CreateGroupClicked : HomeIntent
    data object ActivityClicked : HomeIntent
}

data class HomeState(
    val amountYouOwe: String = "0.00",
    val amountOwedBack: String = "0.00",
    val groupCount: Int = 0,
    val friendCount: Int = 0,
    val recentGroups: List<HomeGroupUi> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: DongiText? = null,
) : MviState

data class HomeGroupUi(
    val name: String,
    val memberCount: Int,
    val balance: String?,
)

sealed interface HomeEffect : MviEffect {
    data object NavigateToGroups : HomeEffect
    data object NavigateToActivity : HomeEffect
}
