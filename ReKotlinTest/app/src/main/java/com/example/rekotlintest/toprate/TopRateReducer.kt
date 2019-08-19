package com.example.rekotlintest.toprate

import org.rekotlin.Action
import timber.log.Timber

class TopRateReducer {
    companion object {
        fun topRateReducer(action: Action, state: TopRateState) : TopRateState {
            Timber.d("topRateReducer: $action")
            val newState = state ?: TopRateState()
            when (action) {
                is TopRateAction.LoadTopRateMoviesStarted -> return newState.copy(isLoading = true)
                is TopRateAction.LoadTopRateMoviesFailed -> return newState.copy(isLoading = false, error = "error occured")
                is TopRateAction.LoadTopRateMoviesCompleted -> return newState.copy(isLoading = false, topRateMovies = action.response)
            }

            return newState
        }
    }
}