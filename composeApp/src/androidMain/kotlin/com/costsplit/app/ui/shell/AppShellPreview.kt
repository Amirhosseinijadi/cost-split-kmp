package com.costsplit.app.ui.shell

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.costsplit.app.navigation.AppRoute
import com.costsplit.core.ui.theme.CostSplitTheme
import com.costsplit.core.ui.strings.DongiString
import com.costsplit.core.ui.strings.dongiString

@Preview(showBackground = true)
@Composable
private fun AppShellPreview() {
    CostSplitTheme {
        AppShell(
            currentRoute = AppRoute.Home,
            onDestinationSelected = {},
        ) {
            Text(dongiString(DongiString.AppName))
        }
    }
}
