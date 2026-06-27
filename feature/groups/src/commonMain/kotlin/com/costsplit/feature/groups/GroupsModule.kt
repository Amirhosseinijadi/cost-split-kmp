package com.costsplit.feature.groups

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val groupsModule = module {
    viewModel { GroupsViewModel() }
}
