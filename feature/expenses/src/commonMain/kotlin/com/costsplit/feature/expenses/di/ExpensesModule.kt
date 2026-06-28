package com.costsplit.feature.expenses.di

import com.costsplit.feature.expenses.data.remote.ExpenseRemoteDataSourceImpl
import com.costsplit.feature.expenses.data.remote.dataSource.ExpenseRemoteDataSource
import com.costsplit.feature.expenses.data.repository.ExpenseRepositoryImpl
import com.costsplit.feature.expenses.domain.repository.ExpenseRepository
import com.costsplit.feature.expenses.domain.usecase.AddExpenseUseCase
import com.costsplit.feature.expenses.domain.usecase.GetExpensesUseCase
import com.costsplit.feature.expenses.presentation.ExpensesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun expensesModule(baseUrl: String) = module {
    single<ExpenseRemoteDataSource> { ExpenseRemoteDataSourceImpl(get(), baseUrl) }
    single<ExpenseRepository> { ExpenseRepositoryImpl(get()) }
    factory { GetExpensesUseCase(get()) }
    factory { AddExpenseUseCase(get()) }
    viewModel { ExpensesViewModel(get(), get()) }
}
