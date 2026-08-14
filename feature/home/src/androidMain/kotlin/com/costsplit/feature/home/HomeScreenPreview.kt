package com.costsplit.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.costsplit.core.ui.theme.CostSplitTheme
import com.costsplit.feature.home.presentation.HomeGroupUi
import com.costsplit.feature.home.presentation.HomeScreen
import com.costsplit.feature.home.presentation.HomeState

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    CostSplitTheme {
        HomeScreen(
            state = HomeState(
                recentGroups = listOf(
                    HomeGroupUi("سفر شمال", "۴ عضو", "+$248.60"),
                    HomeGroupUi("خانه", "۳ عضو", "-$76.10"),
                    HomeGroupUi("ناهار شرکت", "۶ عضو", "+$34.20"),
                ),
            ),
            onIntent = {},
        )
    }
}
