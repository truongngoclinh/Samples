package com.example.rekotlintest

import com.example.rekotlintest.core.ActivityState
import com.example.rekotlintest.favorite.FavoriteState
import com.example.rekotlintest.toprate.TopRateState
import org.rekotlin.StateType
import org.rekotlinrouter.HasNavigationState
import org.rekotlinrouter.NavigationState

data class AppState(
    override var navigationState: NavigationState,
    val activityState: ActivityState = ActivityState(),
    var topRateState: TopRateState,
    var favoriteState: FavoriteState
) : StateType, HasNavigationState