package com.example.rekotlintest.core

import org.rekotlin.Action
import org.rekotlin.DispatchFunction
import org.rekotlin.Middleware
import org.rekotlin.StateType

/**
 * Middleware that supports the use of Thunks.
 * https://github.com/reduxjs/redux-thunk
 * https://github.com/ReSwift/ReSwift-Thunk
 */
@Suppress("UNCHECKED_CAST")
fun <State : StateType> createThunkMiddleware(): Middleware<State> =
    { dispatch, getState ->
        { next ->
            { action ->
                when (val thunk = action as? Thunk<State>) {
                    is Thunk<*> -> thunk.body(dispatch, getState)
                    else -> next(action)
                }
            }
        }
    }

open class Thunk<State : StateType>(val body: (dispatch: DispatchFunction, getState: () -> State?) -> Unit) : Action
