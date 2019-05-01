package com.linhtruong.sample.explore.repo

import com.linhtruong.sample.explore.model.NewsResponse
import io.reactivex.Observable


/**
 * @author linhtruong
 */
interface MainRepository {
    fun getNews(): Observable<NewsResponse>
}