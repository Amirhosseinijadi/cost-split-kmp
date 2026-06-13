package com.costsplit.feature.expenses.di

import com.costsplit.feature.expenses.data.remote.ExpenseApi
import com.costsplit.feature.expenses.data.repository.DefaultExpenseRepository
import com.costsplit.feature.expenses.domain.repository.ExpenseRepository
import com.costsplit.feature.expenses.domain.usecase.AddExpenseUseCase
import com.costsplit.feature.expenses.domain.usecase.GetExpensesUseCase
import com.costsplit.feature.expenses.presentation.ExpensesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun expensesModule(baseUrl: String) = module {
    single { ExpenseApi(get(), baseUrl) }
    single<ExpenseRepository> { DefaultExpenseRepository(get()) }
    factory { GetExpensesUseCase(get()) }
    factory { AddExpenseUseCase(get()) }
    viewModel { ExpensesViewModel(get(), get()) }
}

