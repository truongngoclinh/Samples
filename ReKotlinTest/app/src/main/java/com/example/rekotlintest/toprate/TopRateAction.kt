package com.example.rekotlintest.toprate

import com.example.rekotlintest.AppService
import com.example.rekotlintest.AppState
import com.example.rekotlintest.core.Thunk
import com.example.rekotlintest.core.network.NetworkConst
import com.example.rekotlintest.data.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.rekotlin.Action

object TopRateAction {
    class LoadTopRateMovies(
            private val scope: CoroutineScope,
            private val io: CoroutineDispatcher,
            private val api: AppService
    ) : () -> Thunk<AppState> {
        override fun invoke() = Thunk<AppState> { dispatch, getState ->
            dispatch(LoadTopRateMoviesStarted())
            scope.launch {
                val response = withContext(io) {
                    api.getTopRateMovies(NetworkConst.API_KEY).execute()
                }

                if (response.isSuccessful) {
                    val results = ArrayList<Movie>()
                    results.addAll(response.body()!!.results)
                    dispatch(LoadTopRateMoviesCompleted(response = results))
                } else {
                    dispatch(LoadTopRateMoviesFailed(response.errorBody().toString()))
                }
            }
        }
    }

    class LoadTopRateMoviesStarted : Action
    class LoadTopRateMoviesCompleted(val response: ArrayList<Movie>) : Action
    class LoadTopRateMoviesFailed(val error: String = "") : Action
}