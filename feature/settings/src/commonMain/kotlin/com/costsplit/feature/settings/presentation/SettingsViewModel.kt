package com.costsplit.feature.settings.presentation

import com.costsplit.core.common.mvi.BaseMviViewModel

class SettingsViewModel : BaseMviViewModel<SettingsIntent, SettingsState, SettingsEffect>(
    SettingsState(),
) {
    override fun handleIntent(intent: SettingsIntent) {
        when (intent) {
            is SettingsIntent.NotificationsChanged -> updateState {
                copy(notificationsEnabled = intent.enabled)
            }
        }
    }
}
