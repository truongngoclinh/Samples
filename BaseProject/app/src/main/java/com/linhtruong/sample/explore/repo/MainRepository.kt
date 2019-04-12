package com.linhtruong.sample.explore.repo

import io.reactivex.disposables.Disposable
import com.linhtruong.sample.core.exception.Failure
import com.linhtruong.sample.core.functional.Either
import com.linhtruong.sample.explore.model.NewsResponse


/**
 * @author linhtruong
 */
interface MainRepository {
    fun getNews(onResult: (Either<Failure, NewsResponse>) -> Unit): Disposable
}