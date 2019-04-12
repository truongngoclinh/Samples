package com.linhtruong.sample.explore.repo

import io.reactivex.disposables.Disposable
import com.linhtruong.sample.core.exception.Failure
import com.linhtruong.sample.core.extension.waitOnUI
import com.linhtruong.sample.core.functional.Either
import com.linhtruong.sample.core.platform.base.BaseRepository
import com.linhtruong.sample.explore.model.NewsResponse
import retrofit2.Retrofit
import javax.inject.Inject


/**
 * @author linhtruong
 */
class MainRepositoryImpl
@Inject constructor(private val retrofit: Retrofit) : MainRepository, BaseRepository() {
    private val api by lazy { retrofit.create(MainApi::class.java) }

    override fun getNews(onResult: (Either<Failure, NewsResponse>) -> Unit): Disposable {
        return request(api.getNews().waitOnUI(), onResult, NewsResponse())
    }
}