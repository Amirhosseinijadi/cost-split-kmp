package com.costsplit.core.network.di

import com.costsplit.core.network.createHttpClient
import io.ktor.client.HttpClient
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> { createHttpClient() }
}
