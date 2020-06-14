package com.example.rekotlintest.favorite

import com.example.rekotlintest.data.Movie
import org.rekotlin.StateType

class FavoriteState(
    val isLoading: Boolean = false,
    val favoriteMovies: ArrayList<Movie> = ArrayList(),
    val error: String = ""
) : StateType {
}