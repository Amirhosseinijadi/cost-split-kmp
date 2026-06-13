package com.costsplit.core.common.mvi

import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DefaultMviDelegateTest {
    @Test
    fun updatesStateWithReducer() {
        val delegate = DefaultMviDelegate<TestState, TestEffect>(TestState())

        delegate.updateState { copy(value = value + 1) }

        assertEquals(TestState(value = 1), delegate.state.value)
        assertEquals(delegate.state.value, delegate.currentState)
    }

    @Test
    fun emitsOneOffEffect() = runTest {
        val delegate = DefaultMviDelegate<TestState, TestEffect>(TestState())
        val receivedEffect = async { delegate.effects.first() }

        delegate.emitEffect(TestEffect.Completed)

        assertEquals(TestEffect.Completed, receivedEffect.await())
    }
}

private data class TestState(val value: Int = 0) : MviState

private sealed interface TestEffect : MviEffect {
    data object Completed : TestEffect
}

