package com.costsplit.feature.users.di

import com.costsplit.feature.users.data.remote.UserRemoteDataSourceImpl
import com.costsplit.feature.users.data.remote.dataSource.UserRemoteDataSource
import com.costsplit.feature.users.data.repository.UserRepositoryImpl
import com.costsplit.feature.users.domain.repository.UserRepository
import com.costsplit.feature.users.domain.usecase.CreateUserUseCase
import com.costsplit.feature.users.domain.usecase.GetUserUseCase
import com.costsplit.feature.users.domain.usecase.GetUsersUseCase
import org.koin.dsl.module

fun usersModule(baseUrl: String) = module {
    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get(), baseUrl, get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    factory { CreateUserUseCase(get()) }
    factory { GetUserUseCase(get()) }
    factory { GetUsersUseCase(get()) }
}
