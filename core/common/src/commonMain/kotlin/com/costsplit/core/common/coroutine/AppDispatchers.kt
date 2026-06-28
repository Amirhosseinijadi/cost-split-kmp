package com.costsplit.core.common.coroutine

import kotlinx.coroutines.CoroutineDispatcher

data class AppDispatchers(
    val io: CoroutineDispatcher,
)

expect fun defaultAppDispatchers(): AppDispatchers
