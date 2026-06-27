package com.costsplit.feature.groups

import androidx.lifecycle.viewModelScope
import com.costsplit.core.common.mvi.BaseMviViewModel
import kotlinx.coroutines.launch

class GroupsViewModel : BaseMviViewModel<GroupsIntent, GroupsState, GroupsEffect>(
    GroupsState(
        groups = listOf(
            GroupUi("summer-trip", "Summer Trip", "4 members", "$248.60", 0.72f, "Alex pays you $42.80"),
            GroupUi("apartment", "Apartment", "3 members", "$76.10", 0.36f, "You pay Mina $18.30"),
            GroupUi("office-lunch", "Office Lunch", "6 members", "$34.20", 0.22f, "Sara pays you $9.40"),
            GroupUi("family", "Family", "5 members", "$0.00", 1f, "All settled"),
        ),
        expenses = mapOf(
            "summer-trip" to listOf(
                GroupExpenseUi("Hotel deposit", "$420.00"),
                GroupExpenseUi("Dinner", "$86.40"),
                GroupExpenseUi("Museum tickets", "$64.00"),
            ),
            "apartment" to listOf(
                GroupExpenseUi("Internet", "$35.00"),
                GroupExpenseUi("Groceries", "$41.10"),
            ),
            "office-lunch" to listOf(
                GroupExpenseUi("Sandwich order", "$34.20"),
            ),
            "family" to listOf(
                GroupExpenseUi("Weekend supplies", "$58.10"),
            ),
        ),
    ),
) {
    override fun handleIntent(intent: GroupsIntent) {
        when (intent) {
            GroupsIntent.AddGroupClicked -> Unit
            is GroupsIntent.GroupClicked -> viewModelScope.launch {
                emitEffect(GroupsEffect.NavigateToGroupDetails(intent.groupId))
            }
        }
    }
}
