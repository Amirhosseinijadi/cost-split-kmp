package com.costsplit.feature.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.costsplit.core.ui.theme.CostSplitTheme
import com.costsplit.feature.settings.presentation.SettingUi
import com.costsplit.feature.settings.presentation.SettingsScreen
import com.costsplit.feature.settings.presentation.SettingsState

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    CostSplitTheme {
        SettingsScreen(
            state = SettingsState(
                notificationsEnabled = true,
                settings = listOf(
                    SettingUi("واحد پول پیش‌فرض", "دلار آمریکا"),
                    SettingUi("روش تقسیم", "تقسیم مساوی"),
                    SettingUi("یادآوری پرداخت", "هر جمعه"),
                ),
            ),
            onIntent = {},
        )
    }
}
