package com.example.rekotlintest.data

data class Movie(
    val title: String,
    val vote_average: Double,
    val poster_path: String,
    val overview: String,
    val is_favorite: Boolean = false
)