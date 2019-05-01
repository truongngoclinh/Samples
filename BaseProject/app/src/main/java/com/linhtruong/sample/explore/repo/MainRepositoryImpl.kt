package com.linhtruong.sample.explore.repo

import com.linhtruong.sample.core.extension.waitOnUI
import com.linhtruong.sample.core.platform.base.BaseRepository
import com.linhtruong.sample.explore.model.NewsResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject


/**
 * @author linhtruong
 */
class MainRepositoryImpl
@Inject constructor(private val retrofit: Retrofit) : MainRepository, BaseRepository() {
    override fun getNews(): Observable<NewsResponse> {
        return api.getNews().waitOnUI()
    }

    private val api by lazy { retrofit.create(MainApi::class.java) }

//    override fun getNews(onResult: (Either<Failure, NewsResponse>) -> Unit): Disposable {
//        return request(api.getNews().waitOnUI(), onResult, NewsResponse())
//    }
}