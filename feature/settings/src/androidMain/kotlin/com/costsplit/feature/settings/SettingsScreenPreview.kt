package com.costsplit.feature.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.costsplit.core.ui.theme.CostSplitTheme

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    CostSplitTheme {
        SettingsScreen(
            state = SettingsState(
                notificationsEnabled = true,
                settings = listOf(
                    SettingUi("Default currency", "USD"),
                    SettingUi("Split method", "Equal split"),
                    SettingUi("Payment reminders", "Every Friday"),
                ),
            ),
            onIntent = {},
        )
    }
}
