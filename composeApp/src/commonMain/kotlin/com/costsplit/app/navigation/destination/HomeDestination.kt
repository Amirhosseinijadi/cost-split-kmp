package com.costsplit.app.navigation.destination

import com.costsplit.app.navigation.AppDestination
import com.costsplit.app.navigation.AppRoute
import com.costsplit.app.navigation.NavEntry
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.costsplit.feature.home.presentation.HomeEffect
import com.costsplit.feature.home.presentation.HomeScreen
import com.costsplit.feature.home.presentation.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

val homeDestination = AppDestination { route, navigator ->
    when (route) {
        AppRoute.Home -> NavEntry(route) {
            val viewModel = koinViewModel<HomeViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            CollectEffectWithLifecycle(viewModel.effects) { effect ->
                when (effect) {
                    HomeEffect.NavigateToActivity -> navigator.navigate(AppRoute.Activity)
                    HomeEffect.NavigateToGroups -> navigator.navigate(AppRoute.Groups)
                }
            }
            HomeScreen(
                state = state,
                onIntent = viewModel::onIntent,
            )
        }

        else -> null
    }
}
