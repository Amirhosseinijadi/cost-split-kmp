package com.costsplit.app.navigation.destination

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.costsplit.app.navigation.AppDestination
import com.costsplit.app.navigation.AppRoute
import com.costsplit.app.navigation.NavEntry
import com.costsplit.feature.groups.presentation.detail.GroupDetailsScreen
import com.costsplit.feature.groups.presentation.GroupsEffect
import com.costsplit.feature.groups.presentation.GroupsScreen
import com.costsplit.feature.groups.presentation.GroupsViewModel
import org.koin.compose.viewmodel.koinViewModel

val groupsDestination = AppDestination { route, navigator ->
    when (route) {
        AppRoute.Groups -> NavEntry(route) {
            val viewModel = koinViewModel<GroupsViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            CollectEffectWithLifecycle(viewModel.effects) { effect ->
                when (effect) {
                    is GroupsEffect.NavigateToGroupDetails -> {
                        navigator.navigate(AppRoute.GroupDetails(effect.groupId))
                    }
                }
            }
            GroupsScreen(
                state = state,
                onIntent = viewModel::onIntent,
            )
        }

        else -> null
    }
}

val groupDetailsDestination = AppDestination { route, _ ->
    when (route) {
        is AppRoute.GroupDetails -> NavEntry(route) {
            val viewModel = koinViewModel<GroupsViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            GroupDetailsScreen(
                group = state.group(route.groupId),
                expenses = state.groupExpenses(route.groupId),
            )
        }

        else -> null
    }
}
