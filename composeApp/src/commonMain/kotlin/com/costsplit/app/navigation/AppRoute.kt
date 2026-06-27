package com.costsplit.app.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute : NavKey {
    @Serializable
    data object Home : AppRoute

    @Serializable
    data object Activity : AppRoute

    @Serializable
    data object Groups : AppRoute

    @Serializable
    data object Settings : AppRoute

    @Serializable
    data class GroupDetails(val groupId: String) : AppRoute
}
