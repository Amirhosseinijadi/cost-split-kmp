package com.costsplit.feature.groups.di

import com.costsplit.feature.groups.data.remote.GroupRemoteDataSourceImpl
import com.costsplit.feature.groups.data.remote.dataSource.GroupRemoteDataSource
import com.costsplit.feature.groups.presentation.GroupsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun groupsModule(baseUrl: String) = module {
    single<GroupRemoteDataSource> { GroupRemoteDataSourceImpl(get(), baseUrl) }
    viewModel { GroupsViewModel() }
}
