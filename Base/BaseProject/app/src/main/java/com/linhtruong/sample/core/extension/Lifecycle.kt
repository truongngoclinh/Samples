package com.linhtruong.sample.core.extension

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import com.linhtruong.sample.core.exception.Failure

/**
 * @author linhtruong
 */
fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
        liveData.observe(this, Observer(body))

fun <L : LiveData<Failure>> LifecycleOwner.failure(liveData: L, body: (Failure?) -> Unit) =
        liveData.observe(this, Observer(body))

fun <L : LiveData<Boolean>> LifecycleOwner.loading(liveData: L, body: (Boolean?) -> Unit) =
        liveData.observe(this, Observer(body))
