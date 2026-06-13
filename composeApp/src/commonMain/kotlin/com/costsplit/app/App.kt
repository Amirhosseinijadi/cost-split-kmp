package com.costsplit.app

import androidx.compose.runtime.Composable
import com.costsplit.feature.expenses.presentation.ExpensesScreen
import com.costsplit.feature.expenses.presentation.ExpensesViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    val viewModel = koinViewModel<ExpensesViewModel>()
    ExpensesScreen(viewModel)
}

