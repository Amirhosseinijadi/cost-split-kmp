package com.costsplit.app.navigation.destination

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.costsplit.app.navigation.AppDestination
import com.costsplit.app.navigation.AppRoute
import com.costsplit.app.navigation.NavEntry
import com.costsplit.feature.settings.SettingsScreen
import com.costsplit.feature.settings.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

val settingsDestination = AppDestination { route, _ ->
    when (route) {
        AppRoute.Settings -> NavEntry(route) {
            val viewModel = koinViewModel<SettingsViewModel>()
            val state by viewModel.state.collectAsState()
            SettingsScreen(
                state = state,
                onIntent = viewModel::onIntent,
            )
        }

        else -> null
    }
}
