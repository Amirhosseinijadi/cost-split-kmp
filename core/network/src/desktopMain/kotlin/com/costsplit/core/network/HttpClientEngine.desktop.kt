package com.costsplit.core.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.java.Java

actual fun platformHttpClientEngine(): HttpClientEngine = Java.create()

