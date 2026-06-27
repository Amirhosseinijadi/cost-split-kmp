package com.costsplit.feature.groups

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.costsplit.core.ui.theme.CostSplitTheme

private val previewGroupsState = GroupsState(
    groups = listOf(
        GroupUi("summer-trip", "Summer Trip", "4 members", "$248.60", 0.72f, "Alex pays you $42.80"),
        GroupUi("apartment", "Apartment", "3 members", "$76.10", 0.36f, "You pay Mina $18.30"),
        GroupUi("office-lunch", "Office Lunch", "6 members", "$34.20", 0.22f, "Sara pays you $9.40"),
    ),
    expenses = mapOf(
        "summer-trip" to listOf(
            GroupExpenseUi("Hotel deposit", "$420.00"),
            GroupExpenseUi("Dinner", "$86.40"),
            GroupExpenseUi("Museum tickets", "$64.00"),
        ),
    ),
)

@Preview(showBackground = true)
@Composable
private fun GroupsScreenPreview() {
    CostSplitTheme {
        GroupsScreen(
            state = previewGroupsState,
            onIntent = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GroupDetailsScreenPreview() {
    CostSplitTheme {
        GroupDetailsScreen(
            group = previewGroupsState.group("summer-trip"),
            expenses = previewGroupsState.groupExpenses("summer-trip"),
        )
    }
}
