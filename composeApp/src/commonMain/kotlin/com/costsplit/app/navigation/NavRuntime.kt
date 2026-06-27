package com.costsplit.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList

interface NavKey

class NavEntry<Key : NavKey>(
    val key: Key,
    val content: @Composable () -> Unit,
)

@Composable
fun <Key : NavKey> rememberNavBackStack(root: Key): SnapshotStateList<Key> {
    return remember(root) { mutableStateListOf(root) }
}

@Composable
fun <Key : NavKey> NavDisplay(
    backStack: List<Key>,
    onBack: () -> Unit,
    entryProvider: (Key) -> NavEntry<Key>,
) {
    val entry = entryProvider(backStack.last())
    entry.content()
}
