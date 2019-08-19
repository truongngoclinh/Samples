package com.example.rekotlintest

import com.example.rekotlintest.toprate.TopRateResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {
    @GET("movie/top_rated")
    fun getTopRateMovies(@Query("api_key") apiKey: String) : Call<TopRateResponse>
}