package com.costsplit.core.common.mvi

import androidx.lifecycle.ViewModel

abstract class BaseMviViewModel<
    Intent : MviIntent,
    State : MviState,
    Effect : MviEffect,
>(
    initialState: State,
    private val delegate: MviDelegate<State, Effect> = DefaultMviDelegate(initialState),
) : ViewModel(), MviStateHost<State, Effect> by delegate {
    protected val currentState: State
        get() = delegate.currentState

    fun onIntent(intent: Intent) {
        handleIntent(intent)
    }

    protected abstract fun handleIntent(intent: Intent)

    protected fun updateState(reducer: State.() -> State) {
        delegate.updateState(reducer)
    }

    protected suspend fun emitEffect(effect: Effect) {
        delegate.emitEffect(effect)
    }
}
