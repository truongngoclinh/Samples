package com.example.rekotlintest

import org.rekotlin.Action

class AppReducer {
    companion object {
        fun counterReducer(action: Action, state: Appstate?): Appstate {
            var state = state ?: Appstate()
            when (action) {
                is CounterAction.Decrease -> {
                    state = Appstate(state.counter - 1)
                }
                is CounterAction.Increase -> {
                    state = Appstate(state.counter + 1)
                }
            }

            return state
        }
    }
}