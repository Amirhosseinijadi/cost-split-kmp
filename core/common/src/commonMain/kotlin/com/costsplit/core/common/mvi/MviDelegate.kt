package com.costsplit.core.common.mvi

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

interface MviStateHost<out State : MviState, out Effect : MviEffect> {
    val state: StateFlow<State>
    val effects: Flow<Effect>
}

interface MviDelegate<State : MviState, Effect : MviEffect> : MviStateHost<State, Effect> {
    val currentState: State

    fun updateState(reducer: State.() -> State)

    suspend fun emitEffect(effect: Effect)
}

class DefaultMviDelegate<State : MviState, Effect : MviEffect>(
    initialState: State,
) : MviDelegate<State, Effect> {
    private val mutableState = MutableStateFlow(initialState)
    override val state: StateFlow<State> = mutableState.asStateFlow()
    override val currentState: State
        get() = mutableState.value

    private val effectChannel = Channel<Effect>(Channel.BUFFERED)
    override val effects: Flow<Effect> = effectChannel.receiveAsFlow()

    override fun updateState(reducer: State.() -> State) {
        mutableState.update(reducer)
    }

    override suspend fun emitEffect(effect: Effect) {
        effectChannel.send(effect)
    }
}

