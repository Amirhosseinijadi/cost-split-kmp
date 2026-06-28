package com.costsplit.feature.activity

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val activityModule = module {
    viewModel { ActivityViewModel(get(), get(), get()) }
}
