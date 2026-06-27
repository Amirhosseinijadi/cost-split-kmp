package com.costsplit.app.navigation

fun interface AppDestination {
    fun resolve(route: AppRoute, navigator: AppNavigator): NavEntry<AppRoute>?
}
