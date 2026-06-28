package com.costsplit.feature.groups.presentation

import com.costsplit.core.common.mvi.MviEffect
import com.costsplit.core.common.mvi.MviIntent
import com.costsplit.core.common.mvi.MviState

sealed interface GroupsIntent : MviIntent {
    data class GroupClicked(val groupId: String) : GroupsIntent
    data object AddGroupClicked : GroupsIntent
}

data class GroupsState(
    val title: String = "Groups",
    val subtitle: String = "Shared spaces for every plan.",
    val groups: List<GroupUi> = emptyList(),
    val expenses: Map<String, List<GroupExpenseUi>> = emptyMap(),
) : MviState {
    fun group(groupId: String): GroupUi? = groups.firstOrNull { it.id == groupId }
    fun groupExpenses(groupId: String): List<GroupExpenseUi> = expenses[groupId].orEmpty()
}

data class GroupUi(
    val id: String,
    val name: String,
    val members: String,
    val balance: String,
    val progress: Float,
    val settlement: String,
)

data class GroupExpenseUi(
    val title: String,
    val amount: String,
)

sealed interface GroupsEffect : MviEffect {
    data class NavigateToGroupDetails(val groupId: String) : GroupsEffect
}
