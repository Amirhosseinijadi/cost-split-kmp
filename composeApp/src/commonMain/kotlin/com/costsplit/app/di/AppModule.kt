package com.costsplit.app.di

import com.costsplit.core.network.createHttpClient
import com.costsplit.feature.activity.activityModule
import com.costsplit.feature.expenses.di.expensesModule
import com.costsplit.feature.groups.groupsModule
import com.costsplit.feature.home.homeModule
import com.costsplit.feature.settings.settingsModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(baseUrl: String): KoinApplication = startKoin {
    modules(
        module { single { createHttpClient() } },
        activityModule,
        expensesModule(baseUrl.ensureTrailingSlash()),
        groupsModule,
        homeModule,
        settingsModule,
    )
}

private fun String.ensureTrailingSlash() = if (endsWith('/')) this else "$this/"
