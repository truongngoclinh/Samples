package com.example.rekotlintest.favorite

import org.rekotlin.Action

class FavoriteReducer {
    companion object {
        fun favoriteReducer(action: Action, state: FavoriteState) : FavoriteState {
            val newState = state ?: FavoriteState()

            return newState
        }
    }
}