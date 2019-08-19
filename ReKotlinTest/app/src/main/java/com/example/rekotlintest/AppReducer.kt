package com.example.rekotlintest

import com.example.rekotlintest.core.ActivityState
import com.example.rekotlintest.core.activityLifecycleReducer
import com.example.rekotlintest.favorite.FavoriteReducer.Companion.favoriteReducer
import com.example.rekotlintest.favorite.FavoriteState
import com.example.rekotlintest.toprate.TopRateReducer.Companion.topRateReducer
import com.example.rekotlintest.toprate.TopRateState
import org.rekotlin.Action
import org.rekotlinrouter.NavigationReducer
import org.rekotlinrouter.NavigationState
import org.rekotlinrouter.SetRouteAction
import org.rekotlinrouter.SetRouteSpecificData
import timber.log.Timber

class AppReducer {
    companion object {
        fun appReducer(action: Action, oldState: AppState?): AppState {
            val state = oldState ?: AppState(
                navigationState = NavigationReducer.handleAction(action, oldState?.navigationState),
                activityState = ActivityState(),
                topRateState = TopRateState(),
                favoriteState = FavoriteState()
            )

            return state.copy(
                    navigationState = NavigationReducer.reduce(action, state.navigationState),
//                navigationState = (::navigationReducer)(action, state.navigationState),
                activityState = (::activityLifecycleReducer)(action, state.activityState),
                topRateState = (::topRateReducer)(action, state.topRateState),
                favoriteState = (::favoriteReducer)(action, state.favoriteState)
            )
        }

        fun navigationReducer(action: Action, oldState: NavigationState?): NavigationState {
            Timber.i("action: $action")
            val state = oldState ?: NavigationReducer.handleAction(action = action, state = oldState)
            when (action) {
                is SetRouteAction -> {
                    return NavigationReducer.handleAction(action = action, state = state)
                }

                is SetRouteSpecificData -> {
                    return NavigationReducer.handleAction(action = action, state = state)
                }
            }
            return state
        }
    }
}