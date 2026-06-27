package com.costsplit.app

import androidx.compose.runtime.Composable
import com.costsplit.app.navigation.CostSplitNavHost
import com.costsplit.core.ui.theme.CostSplitTheme

@Composable
fun App() {
    CostSplitTheme {
        CostSplitNavHost()
    }
}
