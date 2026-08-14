package com.costsplit.feature.groups.presentation

import com.costsplit.core.common.mvi.MviEffect
import com.costsplit.core.common.mvi.MviIntent
import com.costsplit.core.common.mvi.MviState

sealed interface GroupsIntent : MviIntent {
    data object Refresh : GroupsIntent
    data class GroupClicked(val groupId: String) : GroupsIntent
    data object AddGroupClicked : GroupsIntent
}

data class GroupsState(
    val title: String = "گروه‌های من",
    val subtitle: String = "هر دورهمی، یک حساب روشن",
    val groups: List<GroupUi> = emptyList(),
    val expenses: Map<String, List<GroupExpenseUi>> = emptyMap(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
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
    val currency: String = "USD",
    val memberBalances: List<MemberBalanceUi> = emptyList(),
)

data class MemberBalanceUi(
    val name: String,
    val amount: String,
)

data class GroupExpenseUi(
    val title: String,
    val amount: String,
    val paidBy: String = "",
    val category: String = "general",
    val date: String = "",
)

sealed interface GroupsEffect : MviEffect {
    data class NavigateToGroupDetails(val groupId: String) : GroupsEffect
}
