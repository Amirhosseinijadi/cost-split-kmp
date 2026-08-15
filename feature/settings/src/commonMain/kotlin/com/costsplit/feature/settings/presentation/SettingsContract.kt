package com.costsplit.feature.settings.presentation

import com.costsplit.core.common.mvi.MviEffect
import com.costsplit.core.common.mvi.MviIntent
import com.costsplit.core.common.mvi.MviState

sealed interface SettingsIntent : MviIntent {
    data class NotificationsChanged(val enabled: Boolean) : SettingsIntent
}

data class SettingsState(
    val notificationsEnabled: Boolean = true,
) : MviState

sealed interface SettingsEffect : MviEffect
