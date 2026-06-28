package com.costsplit.core.common.coroutine

import kotlinx.coroutines.Dispatchers

actual fun defaultAppDispatchers(): AppDispatchers =
    AppDispatchers(io = Dispatchers.IO)
