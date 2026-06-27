package com.costsplit.app.ui.shell

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.costsplit.app.navigation.AppRoute
import com.costsplit.app.navigation.topLevelDestinations

@Composable
fun AppShell(
    currentRoute: AppRoute,
    onDestinationSelected: (AppRoute) -> Unit,
    content: @Composable () -> Unit,
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                topLevelDestinations.forEach { destination ->
                    NavigationBarItem(
                        selected = currentRoute::class == destination.route::class,
                        onClick = { onDestinationSelected(destination.route) },
                        icon = { Text(destination.iconText) },
                        label = { Text(destination.label) },
                    )
                }
            }
        },
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {
            content()
        }
    }
}
