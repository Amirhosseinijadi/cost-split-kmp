package com.costsplit.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.platform.LocalLayoutDirection
import com.costsplit.app.navigation.CostSplitNavHost
import com.costsplit.core.ui.theme.CostSplitTheme

@Composable
fun App() {
    CostSplitTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            CostSplitNavHost()
        }
    }
}
