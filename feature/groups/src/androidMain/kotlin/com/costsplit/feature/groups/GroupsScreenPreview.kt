package com.costsplit.feature.groups

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.costsplit.core.ui.theme.CostSplitTheme
import com.costsplit.core.ui.strings.DongiString
import com.costsplit.core.ui.strings.DongiText
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
            4,
            "$248.60",
            0.72f,
            DongiText(DongiString.SettlementPaysYou, listOf("علی", "$42.80")),
        ),
        GroupUi(
            "apartment",
            "خانه",
            3,
            "-$76.10",
            0.36f,
            DongiText(DongiString.SettlementYouPay, listOf("$18.30", "مینا")),
        ),
        GroupUi(
            "office-lunch",
            "ناهار شرکت",
            6,
            "$34.20",
            0.22f,
            DongiText(DongiString.SettlementPaysYou, listOf("سارا", "$9.40")),
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
