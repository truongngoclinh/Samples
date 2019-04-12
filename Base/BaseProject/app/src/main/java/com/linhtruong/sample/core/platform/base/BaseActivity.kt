package com.linhtruong.sample.core.platform.base

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.linhtruong.sample.R
import kotlinx.android.synthetic.main.activity_base_layout.*
import kotlinx.android.synthetic.main.activity_base_layout.view.*
import kotlinx.android.synthetic.main.activity_base_toolbar.*
import com.linhtruong.sample.core.exception.Failure
import com.linhtruong.sample.core.platform.App
import com.linhtruong.sample.core.platform.Navigator
import news.agoda.com.sample.di.module.di.AppComponent
import timber.log.Timber
import javax.inject.Inject


/**
 * @author linhtruong
 */
abstract class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator
    private lateinit var loadingAnimation: Animation

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as App).appComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView(savedInstanceState)
        initToolbar()
        initLoading()
    }

    open fun initView(savedInstanceState: Bundle?) {
        val view = layoutInflater.inflate(R.layout.activity_base_layout, null)
        val contentView = layoutInflater.inflate(contentView(), null)
        view.container.addView(contentView)
        setContentView(view)
    }

    private fun initToolbar() {
        toolbarTitle.text = getString(title())
        toolbarBack.setOnClickListener { onBackPressed() }

        if (enableBack()) toolbarBack.visibility = View.VISIBLE
    }

    // handle loading spinner
    private fun initLoading() {
        loadingAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_loading_spinner)
    }
    private fun hideLoading() = renderLoading(false)
    open fun renderLoading(status: Boolean?) {
        with(this) {
            status?.let { isLoading ->
                if (isLoading) {
                    this.window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    this.loadingSpinner.visibility = View.VISIBLE
                    this.loadingSpinner.startAnimation(loadingAnimation)
                } else {
                    if (this.loadingSpinner.visibility == View.VISIBLE) {
                        this.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                        this.loadingSpinner.clearAnimation()
                        this.loadingSpinner.visibility = View.GONE
                    }
                }
            }
        }
    }

    // handle message
    private fun notify(message: String, duration: Int) = Snackbar.make(container, message, duration).show()
    fun notify(@StringRes message: Int, duration: Int) = Snackbar.make(container, message, duration).show()

    // handle error
    open fun renderFailure(failure: Failure?) {
        Timber.d("Failure: $failure")
        hideLoading()
        when (failure) {
            is Failure.GeneralFailure -> notify(failure.msg, Snackbar.LENGTH_LONG)
            is Failure.ExceptionError -> notify(failure.e.localizedMessage, Snackbar.LENGTH_LONG)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right)
    }

    open fun enableBack() = false
    abstract fun contentView(): Int
    abstract fun title(): Int
}