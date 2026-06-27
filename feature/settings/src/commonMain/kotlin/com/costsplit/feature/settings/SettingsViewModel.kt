package com.costsplit.feature.settings

import com.costsplit.core.common.mvi.BaseMviViewModel

class SettingsViewModel : BaseMviViewModel<SettingsIntent, SettingsState, SettingsEffect>(
    SettingsState(
        settings = listOf(
            SettingUi("Default currency", "USD"),
            SettingUi("Split method", "Equal split"),
            SettingUi("Payment reminders", "Every Friday"),
        ),
    ),
) {
    override fun handleIntent(intent: SettingsIntent) {
        when (intent) {
            is SettingsIntent.NotificationsChanged -> updateState {
                copy(notificationsEnabled = intent.enabled)
            }
        }
    }
}
