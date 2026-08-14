package com.costsplit.feature.settings.presentation

import com.costsplit.core.common.mvi.BaseMviViewModel

class SettingsViewModel : BaseMviViewModel<SettingsIntent, SettingsState, SettingsEffect>(
    SettingsState(
        settings = listOf(
            SettingUi("واحد پول پیش‌فرض", "دلار آمریکا"),
            SettingUi("روش تقسیم", "تقسیم مساوی"),
            SettingUi("یادآوری پرداخت", "هر جمعه"),
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
