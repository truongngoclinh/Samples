package com.linhtruong.sample.core.platform.base

import android.content.Intent
import android.os.Bundle
import com.linhtruong.sample.R
import com.linhtruong.sample.core.extension.inTransaction


/**
 * @author linhtruong
 */
abstract class BaseFragmentActivity : BaseActivity() {
    override fun initView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_base_layout)
        addFragment(savedInstanceState)
    }

    private fun getFragment() = supportFragmentManager.findFragmentById(R.id.container) as BaseFragment
    private fun addFragment(savedInstanceState: Bundle?) = savedInstanceState
            ?: supportFragmentManager.inTransaction(false) { add(R.id.container, fragment()) }
    fun replaceFragment(fragment: BaseFragment) = supportFragmentManager.inTransaction(true)
    { replace(R.id.container, fragment) }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        getFragment().activityResult(requestCode, resultCode, data)
    }

    abstract fun fragment(): BaseFragment
    override fun contentView(): Int = 0
}