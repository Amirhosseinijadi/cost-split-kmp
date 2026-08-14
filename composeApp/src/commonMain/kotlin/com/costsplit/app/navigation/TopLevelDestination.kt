package com.costsplit.app.navigation

import com.costsplit.core.ui.components.DongiIcon

data class TopLevelDestination(
    val route: AppRoute,
    val icon: DongiIcon,
)

val topLevelDestinations = listOf(
    TopLevelDestination(AppRoute.Home, DongiIcon.Home),
    TopLevelDestination(AppRoute.Groups, DongiIcon.Groups),
    TopLevelDestination(AppRoute.Activity, DongiIcon.Activity),
    TopLevelDestination(AppRoute.Settings, DongiIcon.Settings),
)
