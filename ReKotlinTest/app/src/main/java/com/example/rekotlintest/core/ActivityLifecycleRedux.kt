package com.example.rekotlintest.core

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import org.rekotlin.Action
import org.rekotlin.DispatchFunction
import org.rekotlin.StateType

data class ActivityState(
    val activityLifecycleState: Lifecycle.State = Lifecycle.State.DESTROYED,
    val lastActivityLifecycleEvent: Lifecycle.Event = Lifecycle.Event.ON_ANY
) : StateType

data class ActivityLifecycleEvent(val event: Lifecycle.Event) : Action

fun mapLifecycleEventToState(event: Lifecycle.Event): Lifecycle.State = when (event) {
    Lifecycle.Event.ON_CREATE -> Lifecycle.State.CREATED
    Lifecycle.Event.ON_START -> Lifecycle.State.STARTED
    Lifecycle.Event.ON_RESUME -> Lifecycle.State.RESUMED
    Lifecycle.Event.ON_PAUSE -> Lifecycle.State.STARTED
    Lifecycle.Event.ON_STOP -> Lifecycle.State.CREATED
    Lifecycle.Event.ON_DESTROY -> Lifecycle.State.DESTROYED
    else -> Lifecycle.State.INITIALIZED
}

fun activityLifecycleReducer(action: Action, state: ActivityState?): ActivityState =
    when (action) {
        is ActivityLifecycleEvent -> ActivityState(
            activityLifecycleState = mapLifecycleEventToState(action.event),
            lastActivityLifecycleEvent = action.event
        )
        else -> state ?: ActivityState()
    }

class RootLifecycleObserver(private val dispatch: DispatchFunction) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        dispatch(ActivityLifecycleEvent(Lifecycle.Event.ON_CREATE))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        dispatch(ActivityLifecycleEvent(Lifecycle.Event.ON_START))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        dispatch(ActivityLifecycleEvent(Lifecycle.Event.ON_RESUME))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        dispatch(ActivityLifecycleEvent(Lifecycle.Event.ON_PAUSE))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        dispatch(ActivityLifecycleEvent(Lifecycle.Event.ON_STOP))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        dispatch(ActivityLifecycleEvent(Lifecycle.Event.ON_DESTROY))
    }
}
