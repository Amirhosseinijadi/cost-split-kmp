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
                    ActivityUi("مینا هزینه شام را ثبت کرد", "سفر شمال", "$86.40", "امروز"),
                    ActivityUi("شما با علی تسویه کردید", "خانه", "$24.00", "دیروز"),
                    ActivityUi("سارا خرید خانه را ثبت کرد", "خانواده", "$58.10", "۳ تیر"),
                ),
            ),
            onIntent = {},
        )
    }
}
