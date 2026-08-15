package com.costsplit.feature.activity

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.costsplit.core.ui.theme.CostSplitTheme
import com.costsplit.core.ui.strings.DongiString
import com.costsplit.core.ui.strings.DongiText

@Preview(showBackground = true)
@Composable
private fun ActivityScreenPreview() {
    CostSplitTheme {
        ActivityScreen(
            state = ActivityState(
                activities = listOf(
                    ActivityUi(
                        DongiText(DongiString.ActivityAddedExpense, listOf("مینا", "شام")),
                        "سفر شمال",
                        "$86.40",
                        "امروز",
                    ),
                    ActivityUi(
                        DongiText(DongiString.ActivityAddedExpense, listOf("علی", "اجاره")),
                        "خانه",
                        "$24.00",
                        "دیروز",
                    ),
                ),
            ),
            onIntent = {},
        )
    }
}
