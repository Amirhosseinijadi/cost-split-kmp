package com.costsplit.app.di

import com.costsplit.core.common.di.coroutineModule
import com.costsplit.core.network.di.networkModule
import com.costsplit.feature.activity.activityModule
import com.costsplit.feature.expenses.di.expensesModule
import com.costsplit.feature.groups.di.groupsModule
import com.costsplit.feature.home.di.homeModule
import com.costsplit.feature.settings.di.settingsModule
import com.costsplit.feature.users.di.usersModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin(baseUrl: String): KoinApplication = startKoin {
    modules(
        coroutineModule,
        networkModule,
        activityModule,
        expensesModule(baseUrl.ensureTrailingSlash()),
        groupsModule(baseUrl.ensureTrailingSlash()),
        homeModule,
        settingsModule,
        usersModule(baseUrl.ensureTrailingSlash()),
    )
}

private fun String.ensureTrailingSlash() = if (endsWith('/')) this else "$this/"
