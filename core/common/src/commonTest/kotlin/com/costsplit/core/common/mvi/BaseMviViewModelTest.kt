package com.costsplit.core.common.mvi

import kotlin.test.Test
import kotlin.test.assertEquals

class BaseMviViewModelTest {
    @Test
    fun routesIntentAndPublishesReducedState() {
        val viewModel = TestViewModel()

        viewModel.onIntent(BaseTestIntent.Increment)

        assertEquals(BaseTestState(value = 1), viewModel.state.value)
    }
}

private class TestViewModel :
    BaseMviViewModel<BaseTestIntent, BaseTestState, BaseTestEffect>(BaseTestState()) {

    override fun handleIntent(intent: BaseTestIntent) {
        when (intent) {
            BaseTestIntent.Increment -> updateState { copy(value = value + 1) }
        }
    }
}

private sealed interface BaseTestIntent : MviIntent {
    data object Increment : BaseTestIntent
}

private data class BaseTestState(val value: Int = 0) : MviState

private sealed interface BaseTestEffect : MviEffect
