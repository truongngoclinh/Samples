package com.linhtruong.sample

import android.app.Application
import android.content.Context
import com.linhtruong.sample.core.platform.base.BaseActivity
import com.nhaarman.mockitokotlin2.mock
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config


/**
 * @author linhtruong
 *
 * Base class for Android tests. Inherit from it to create test cases which contain android
 * framework dependencies or components.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class,
        application = AndroidTest.ApplicationStub::class,
        sdk = [21])
abstract class AndroidTest {

    @Suppress("LeakingThis")
    @Rule
    @JvmField
    val injectMocks = InjectMocksRule.create(this@AndroidTest)

    fun activityContext() = mock<BaseActivity>()
    fun context(): Context = RuntimeEnvironment.application

    class ApplicationStub : Application()
}