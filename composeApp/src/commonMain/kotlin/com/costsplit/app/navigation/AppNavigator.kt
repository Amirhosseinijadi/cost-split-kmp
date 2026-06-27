package com.costsplit.app.navigation

import androidx.compose.runtime.Stable
import androidx.compose.runtime.snapshots.SnapshotStateList

@Stable
class AppNavigator(
    private val backStack: SnapshotStateList<AppRoute>,
) {
    val currentRoute: AppRoute
        get() = backStack.lastOrNull() ?: AppRoute.Home

    fun navigate(route: AppRoute) {
        if (currentRoute != route) {
            backStack.add(route)
        }
    }

    fun selectTopLevel(route: AppRoute) {
        val routeIndex = backStack.indexOf(route)
        if (routeIndex >= 0) {
            while (backStack.lastIndex > routeIndex) {
                backStack.removeAt(backStack.lastIndex)
            }
        } else {
            backStack.add(route)
        }
    }

    fun replaceRoot(route: AppRoute) {
        backStack.clear()
        backStack.add(route)
    }

    fun goBack() {
        if (backStack.size > 1) {
            backStack.removeAt(backStack.lastIndex)
        }
    }
}
