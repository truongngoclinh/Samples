package com.linhtruong.sample.core.platform

import android.app.Application
import com.linhtruong.sample.BuildConfig
import com.squareup.leakcanary.LeakCanary
import news.agoda.com.sample.di.module.di.AppComponent
import timber.log.Timber


/**
 * @author linhtruong
 */
class App : Application() {
    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        AppComponent.init(this)
    }

    companion object {
        lateinit var appContext: App
    }

    override fun onCreate() {
        super.onCreate()
        initUtils()
        initAppComponent()
    }

    private fun initUtils() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            LeakCanary.install(this)
        }
    }

    private fun initAppComponent() {
        appComponent.inject(this)
        appContext = this
    }
}