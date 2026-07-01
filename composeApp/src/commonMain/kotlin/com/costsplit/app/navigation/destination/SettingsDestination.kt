package com.costsplit.app.navigation.destination

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.costsplit.app.navigation.AppDestination
import com.costsplit.app.navigation.AppRoute
import com.costsplit.app.navigation.NavEntry
import com.costsplit.feature.settings.presentation.SettingsScreen
import com.costsplit.feature.settings.presentation.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

val settingsDestination = AppDestination { route, _ ->
    when (route) {
        AppRoute.Settings -> NavEntry(route) {
            val viewModel = koinViewModel<SettingsViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            SettingsScreen(
                state = state,
                onIntent = viewModel::onIntent,
            )
        }

        else -> null
    }
}
