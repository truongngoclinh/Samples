package com.linhtruong.sample.explore.repo

import io.reactivex.Observable
import com.linhtruong.sample.explore.model.NewsResponse
import retrofit2.http.GET


/**
 * @author linhtruong
 */
interface MainApi {
    companion object {
        const val NEWS = "bins/nl6jh"
    }

    @GET(NEWS)
    fun getNews()
            : Observable<NewsResponse>
}