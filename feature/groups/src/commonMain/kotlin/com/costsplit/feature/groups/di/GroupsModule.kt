package com.costsplit.feature.groups.di

import com.costsplit.feature.groups.data.remote.GroupRemoteDataSourceImpl
import com.costsplit.feature.groups.data.remote.dataSource.GroupRemoteDataSource
import com.costsplit.feature.groups.data.repository.GroupRepositoryImpl
import com.costsplit.feature.groups.domain.repository.GroupRepository
import com.costsplit.feature.groups.domain.usecase.CreateGroupUseCase
import com.costsplit.feature.groups.domain.usecase.GetGroupBalancesUseCase
import com.costsplit.feature.groups.domain.usecase.GetGroupUseCase
import com.costsplit.feature.groups.domain.usecase.GetUserGroupsUseCase
import com.costsplit.feature.groups.presentation.GroupsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun groupsModule(baseUrl: String) = module {
    single<GroupRemoteDataSource> { GroupRemoteDataSourceImpl(get(), baseUrl, get()) }
    single<GroupRepository> { GroupRepositoryImpl(get()) }
    factory { CreateGroupUseCase(get()) }
    factory { GetGroupUseCase(get()) }
    factory { GetUserGroupsUseCase(get()) }
    factory { GetGroupBalancesUseCase(get()) }
    viewModel { GroupsViewModel(get(), get(), get(), get()) }
}
