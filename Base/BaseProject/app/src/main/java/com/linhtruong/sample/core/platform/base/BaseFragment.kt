package com.linhtruong.sample.core.platform.base

import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.linhtruong.sample.core.exception.Failure
import com.linhtruong.sample.core.platform.App
import com.linhtruong.sample.core.platform.Navigator
import news.agoda.com.sample.di.module.di.AppComponent
import javax.inject.Inject


/**
 * @author linhtruong
 */
abstract class BaseFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as App).appComponent
    }

    abstract fun layoutId(): Int
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(layoutId(), container, false)

    open fun activityResult(requestCode: Int, resultCode: Int, data: Intent?) {}
    open fun renderLoading(status: Boolean?) = (activity as BaseActivity).renderLoading(status)
    open fun renderFailure(failure: Failure?) = (activity as BaseActivity).renderFailure(failure)
}


