package com.costsplit.feature.activity

import com.costsplit.core.common.mvi.BaseMviViewModel

class ActivityViewModel : BaseMviViewModel<ActivityIntent, ActivityState, ActivityEffect>(
    ActivityState(
        activities = listOf(
            ActivityUi("Mina added Dinner", "Summer Trip", "$86.40", "Today"),
            ActivityUi("You settled with Alex", "Apartment", "$24.00", "Yesterday"),
            ActivityUi("Sara added Groceries", "Family", "$58.10", "Jun 24"),
            ActivityUi("Omid joined Office Lunch", "Office Lunch", "$0.00", "Jun 22"),
        ),
    ),
) {
    override fun handleIntent(intent: ActivityIntent) {
        when (intent) {
            ActivityIntent.Refresh -> Unit
        }
    }
}
