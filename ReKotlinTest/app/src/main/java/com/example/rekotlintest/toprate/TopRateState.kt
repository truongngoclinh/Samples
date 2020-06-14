package com.example.rekotlintest.toprate

import com.example.rekotlintest.data.Movie
import org.rekotlin.StateType

data class TopRateState(
    val isLoading: Boolean = false,
    val topRateMovies: ArrayList<Movie> = ArrayList(),
    val error: String = ""
) : StateType {
}