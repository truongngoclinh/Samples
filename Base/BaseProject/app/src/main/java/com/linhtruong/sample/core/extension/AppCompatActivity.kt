package com.linhtruong.sample.core.extension

import android.support.v7.app.AppCompatActivity
import com.linhtruong.sample.R

/**
 * @author linhtruong
 */
fun AppCompatActivity.isTabletLandscapeMode(): Boolean = resources.getBoolean(R.bool.isTablet)

