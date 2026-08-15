package com.costsplit.feature.activity

import com.costsplit.core.common.mvi.MviEffect
import com.costsplit.core.common.mvi.MviIntent
import com.costsplit.core.common.mvi.MviState
import com.costsplit.core.ui.strings.DongiText

sealed interface ActivityIntent : MviIntent {
    data object Refresh : ActivityIntent
}

data class ActivityState(
    val activities: List<ActivityUi> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: DongiText? = null,
) : MviState

data class ActivityUi(
    val title: DongiText,
    val group: String,
    val amount: String,
    val date: String,
)

sealed interface ActivityEffect : MviEffect
