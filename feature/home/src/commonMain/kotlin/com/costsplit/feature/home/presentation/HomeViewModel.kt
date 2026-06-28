package com.costsplit.feature.home.presentation

import androidx.lifecycle.viewModelScope
import com.costsplit.core.common.mvi.BaseMviViewModel
import kotlinx.coroutines.launch

class HomeViewModel : BaseMviViewModel<HomeIntent, HomeState, HomeEffect>(
    HomeState(
        recentGroups = listOf(
            HomeGroupUi("Summer Trip", "4 members", "$248.60 open"),
            HomeGroupUi("Apartment", "3 members", "$76.10 open"),
            HomeGroupUi("Office Lunch", "6 members", "$34.20 open"),
        ),
    ),
) {
    override fun handleIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.CreateGroupClicked -> viewModelScope.launch {
                emitEffect(HomeEffect.NavigateToGroups)
            }

            HomeIntent.ActivityClicked -> viewModelScope.launch {
                emitEffect(HomeEffect.NavigateToActivity)
            }
        }
    }
}
