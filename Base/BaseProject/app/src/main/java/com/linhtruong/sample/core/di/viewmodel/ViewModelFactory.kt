package com.linhtruong.sample.core.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * @author linhtruong
 */
@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory
@Inject constructor(private val creators: Map<Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?:
            creators.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value ?:
            throw Throwable("Unknown ViewModel class $modelClass")

        return try { creator.get() as T }
        catch (e: Exception) { throw RuntimeException(e) }
    }
}

