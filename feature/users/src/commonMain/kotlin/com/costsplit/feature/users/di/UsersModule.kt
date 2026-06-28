package com.costsplit.feature.users.di

import com.costsplit.feature.users.data.remote.UserRemoteDataSourceImpl
import com.costsplit.feature.users.data.remote.dataSource.UserRemoteDataSource
import org.koin.dsl.module

fun usersModule(baseUrl: String) = module {
    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get(), baseUrl) }
}
