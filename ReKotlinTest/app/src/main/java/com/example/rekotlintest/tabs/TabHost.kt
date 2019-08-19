package com.example.rekotlintest.tabs

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.rekotlintest.R
import com.example.rekotlintest.core.ViewComponent
import com.example.rekotlintest.core.attach
import com.example.rekotlintest.core.detach
import com.example.rekotlintest.core.inflate

/**
 * A [ViewComponent] that hosts other [ViewComponent]s and switches between them, routing is handled by [TabRouter].
 */
class TabHost(parent: ViewGroup) : ViewComponent {
    val contentView: FrameLayout = parent.inflate(R.layout.component_tab_host)

    private var currentTab: ViewComponent? = null

    fun navigateToTab(vc: ViewComponent) {
        currentTab?.let { contentView.detach(it) }
        contentView.attach(vc)
        currentTab = vc
    }

    override val view: View
        get() = contentView
}
