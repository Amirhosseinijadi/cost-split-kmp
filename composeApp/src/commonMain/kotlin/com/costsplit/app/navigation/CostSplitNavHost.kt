package com.costsplit.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.costsplit.app.navigation.destination.activityDestination
import com.costsplit.app.navigation.destination.groupDetailsDestination
import com.costsplit.app.navigation.destination.groupsDestination
import com.costsplit.app.navigation.destination.homeDestination
import com.costsplit.app.navigation.destination.settingsDestination
import com.costsplit.app.ui.shell.AppShell

@Composable
fun CostSplitNavHost() {
    val backStack = rememberNavBackStack<AppRoute>(AppRoute.Home)
    val navigator = remember(backStack) { AppNavigator(backStack) }
    val destinations = remember {
        listOf(
            homeDestination,
            groupsDestination,
            groupDetailsDestination,
            activityDestination,
            settingsDestination,
        )
    }

    AppShell(
        currentRoute = navigator.currentRoute,
        onDestinationSelected = navigator::selectTopLevel,
    ) {
        NavDisplay(
            backStack = backStack,
            onBack = { navigator.goBack() },
            entryProvider = { route ->
                destinations.firstNotNullOfOrNull { destination ->
                    destination.resolve(route, navigator)
                } ?: NavEntry(route) {
                    UnknownRouteScreen(route)
                }
            },
        )
    }
}
