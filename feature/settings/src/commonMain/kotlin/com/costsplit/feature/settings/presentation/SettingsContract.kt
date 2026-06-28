package com.costsplit.feature.settings.presentation

import com.costsplit.core.common.mvi.MviEffect
import com.costsplit.core.common.mvi.MviIntent
import com.costsplit.core.common.mvi.MviState

sealed interface SettingsIntent : MviIntent {
    data class NotificationsChanged(val enabled: Boolean) : SettingsIntent
}

data class SettingsState(
    val title: String = "Settings",
    val subtitle: String = "Personalize your cost splitting workflow.",
    val notificationsEnabled: Boolean = true,
    val settings: List<SettingUi> = emptyList(),
) : MviState

data class SettingUi(
    val title: String,
    val value: String,
)

sealed interface SettingsEffect : MviEffect
