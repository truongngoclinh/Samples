package com.linhtruong.sample.core.platform.base

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import com.linhtruong.sample.core.exception.Failure
import com.linhtruong.sample.core.functional.Either
import com.linhtruong.sample.core.functional.Either.Right
import com.linhtruong.sample.core.functional.Either.Left
import com.linhtruong.sample.core.network.CommonResponse
import com.linhtruong.sample.core.network.NetworkConst
import timber.log.Timber


/**
 * @author linhtruong
 */
abstract class BaseRepository {
    fun <T> request(observable: Observable<T>, onResult: (Either<Failure, T>) -> Unit, default: T): Disposable {
        return observable.subscribe(
                { response ->
                    try {
                        val commonResponse = response as CommonResponse
                        if (commonResponse.status.equals(NetworkConst.OK, true)) {
                            onResult(Right(response))
                        }
                    } catch (e: Throwable) {
                        Timber.e("Common response casts failed!")
                        onResult(Right(default))
                    }
                },
                { e ->
                    Timber.e("Exception ${e.localizedMessage}")
                    onResult(Left(Failure.ExceptionError(e)))
                }
        )
    }
}