package com.costsplit.app.navigation.destination

import com.costsplit.app.navigation.AppDestination
import com.costsplit.app.navigation.NavEntry
import com.costsplit.app.navigation.AppRoute
import kotlinx.coroutines.flow.collectLatest
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel
import com.costsplit.feature.home.HomeEffect
import com.costsplit.feature.home.HomeScreen
import com.costsplit.feature.home.HomeViewModel

val homeDestination = AppDestination { route, navigator ->
    when (route) {
        AppRoute.Home -> NavEntry(route) {
            val viewModel = koinViewModel<HomeViewModel>()
            val state by viewModel.state.collectAsState()
            LaunchedEffect(viewModel) {
                viewModel.effects.collectLatest { effect ->
                    when (effect) {
                        HomeEffect.NavigateToActivity -> navigator.navigate(AppRoute.Activity)
                        HomeEffect.NavigateToGroups -> navigator.navigate(AppRoute.Groups)
                    }
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
