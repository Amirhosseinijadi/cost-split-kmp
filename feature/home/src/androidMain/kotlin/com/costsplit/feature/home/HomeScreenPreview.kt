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
                    HomeGroupUi("Summer Trip", "4 members", "$248.60 open"),
                    HomeGroupUi("Apartment", "3 members", "$76.10 open"),
                    HomeGroupUi("Office Lunch", "6 members", "$34.20 open"),
                ),
            ),
            onIntent = {},
        )
    }
}
