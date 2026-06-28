package com.costsplit.core.common.di

import com.costsplit.core.common.coroutine.AppDispatchers
import com.costsplit.core.common.coroutine.defaultAppDispatchers
import org.koin.dsl.module

val coroutineModule = module {
    single<AppDispatchers> { defaultAppDispatchers() }
}
