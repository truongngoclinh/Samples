package com.linhtruong.sample.explore

import com.linhtruong.sample.R
import com.linhtruong.sample.core.platform.base.BaseActivity


/**
 * @author linhtruong
 */
class MainActivity : BaseActivity() {
    override fun title(): Int = R.string.label_app_name
    override fun contentView(): Int = R.layout.activity_main
}