package com.example.rekotlintest.core

import com.example.rekotlintest.AppState
import org.rekotlin.DispatchFunction
import timber.log.Timber

@Suppress("UNUSED_PARAMETER", "MagicNumber")
fun loggingMiddleware(dispatch: DispatchFunction, getState: () -> AppState?): (DispatchFunction) -> DispatchFunction =
    { next ->
        { action ->
            val stack = Exception().stackTrace
            val ste = if (stack[2].className == "org.rekotlin.Store") stack[3] else stack[2]
            val from = "from ${ste.className.split('.').last()}#${ste.methodName} in ${ste.fileName}[${ste.lineNumber}]"
            Timber.d("🌳 Action: $action from $from")
            Timber.d("🌳 State: ${getState()}")
            next(action)
        }
    }
