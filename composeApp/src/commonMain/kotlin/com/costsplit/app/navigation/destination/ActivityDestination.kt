package com.costsplit.app.navigation.destination

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.costsplit.app.navigation.AppDestination
import com.costsplit.app.navigation.AppRoute
import com.costsplit.app.navigation.NavEntry
import com.costsplit.feature.activity.ActivityScreen
import com.costsplit.feature.activity.ActivityViewModel
import org.koin.compose.viewmodel.koinViewModel

val activityDestination = AppDestination { route, _ ->
    when (route) {
        AppRoute.Activity -> NavEntry(route) {
            val viewModel = koinViewModel<ActivityViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            ActivityScreen(
                state = state,
                onIntent = viewModel::onIntent,
            )
        }

        else -> null
    }
}
