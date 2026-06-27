package com.costsplit.app.navigation

data class TopLevelDestination(
    val route: AppRoute,
    val label: String,
    val iconText: String,
)

val topLevelDestinations = listOf(
    TopLevelDestination(AppRoute.Home, "Home", "H"),
    TopLevelDestination(AppRoute.Groups, "Groups", "G"),
    TopLevelDestination(AppRoute.Activity, "Activity", "A"),
    TopLevelDestination(AppRoute.Settings, "Settings", "S"),
)
