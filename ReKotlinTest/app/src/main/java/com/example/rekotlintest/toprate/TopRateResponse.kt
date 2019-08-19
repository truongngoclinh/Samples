package com.example.rekotlintest.toprate

import com.example.rekotlintest.data.Movie

data class TopRateResponse(
    val results: List<Movie>,
    val page: Double
)