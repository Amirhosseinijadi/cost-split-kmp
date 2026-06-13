package com.costsplit.app.di

import com.costsplit.core.network.createHttpClient
import com.costsplit.feature.expenses.di.expensesModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(baseUrl: String): KoinApplication = startKoin {
    modules(
        module { single { createHttpClient() } },
        expensesModule(baseUrl.ensureTrailingSlash()),
    )
}

private fun String.ensureTrailingSlash() = if (endsWith('/')) this else "$this/"

