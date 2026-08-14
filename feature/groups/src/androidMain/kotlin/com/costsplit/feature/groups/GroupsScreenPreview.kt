package com.costsplit.feature.groups

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.costsplit.core.ui.theme.CostSplitTheme
import com.costsplit.feature.groups.presentation.GroupExpenseUi
import com.costsplit.feature.groups.presentation.GroupUi
import com.costsplit.feature.groups.presentation.GroupsScreen
import com.costsplit.feature.groups.presentation.GroupsState
import com.costsplit.feature.groups.presentation.detail.GroupDetailsScreen

private val previewGroupsState = GroupsState(
    groups = listOf(
        GroupUi(
            "summer-trip",
            "سفر شمال",
            "۴ عضو",
            "$248.60",
            0.72f,
            "علی باید $42.80 به شما بپردازد"
        ),
        GroupUi("apartment", "خانه", "۳ عضو", "-$76.10", 0.36f, "شما باید $18.30 به مینا بپردازید"),
        GroupUi(
            "office-lunch",
            "ناهار شرکت",
            "۶ عضو",
            "$34.20",
            0.22f,
            "سارا باید $9.40 به شما بپردازد"
        ),
    ),
    expenses = mapOf(
        "summer-trip" to listOf(
            GroupExpenseUi("بیعانه هتل", "$420.00", "علی", "travel", "۱۴۰۵/۰۵/۲۰"),
            GroupExpenseUi("شام", "$86.40", "مینا", "food", "۱۴۰۵/۰۵/۱۹"),
            GroupExpenseUi("بلیط موزه", "$64.00", "سارا", "travel", "۱۴۰۵/۰۵/۱۸"),
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
