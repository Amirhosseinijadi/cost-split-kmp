package com.costsplit.feature.activity

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.costsplit.core.ui.theme.CostSplitTheme

@Preview(showBackground = true)
@Composable
private fun ActivityScreenPreview() {
    CostSplitTheme {
        ActivityScreen(
            state = ActivityState(
                activities = listOf(
                    ActivityUi("Mina added Dinner", "Summer Trip", "$86.40", "Today"),
                    ActivityUi("You settled with Alex", "Apartment", "$24.00", "Yesterday"),
                    ActivityUi("Sara added Groceries", "Family", "$58.10", "Jun 24"),
                ),
            ),
            onIntent = {},
        )
    }
}
