package com.costsplit.feature.activity

import com.costsplit.core.common.mvi.MviEffect
import com.costsplit.core.common.mvi.MviIntent
import com.costsplit.core.common.mvi.MviState

sealed interface ActivityIntent : MviIntent {
    data object Refresh : ActivityIntent
}

data class ActivityState(
    val title: String = "فعالیت‌ها",
    val subtitle: String = "همه‌ی تغییرات حساب‌ها در یک نگاه",
    val activities: List<ActivityUi> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
) : MviState

data class ActivityUi(
    val title: String,
    val group: String,
    val amount: String,
    val date: String,
)

sealed interface ActivityEffect : MviEffect
