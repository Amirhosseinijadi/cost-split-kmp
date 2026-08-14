package com.costsplit.app.navigation

import com.costsplit.core.ui.components.DongiIcon

data class TopLevelDestination(
    val route: AppRoute,
    val label: String,
    val icon: DongiIcon,
)

val topLevelDestinations = listOf(
    TopLevelDestination(AppRoute.Home, "خانه", DongiIcon.Home),
    TopLevelDestination(AppRoute.Groups, "گروه‌ها", DongiIcon.Groups),
    TopLevelDestination(AppRoute.Activity, "فعالیت‌ها", DongiIcon.Activity),
    TopLevelDestination(AppRoute.Settings, "تنظیمات", DongiIcon.Settings),
)
