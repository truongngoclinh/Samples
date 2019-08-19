package com.example.rekotlintest.core.base

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.rekotlintest.R
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity : AppCompatActivity() {
    lateinit var loadingAnimation : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadingAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_loading_spinner)
    }

    fun renderLoading(status: Boolean?) {
        runOnUiThread {
            with(this) {
                status?.let { isLoading ->
                    if (isLoading) {
                        this.window.setFlags(
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                        )
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
    }
}