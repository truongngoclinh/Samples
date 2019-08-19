package com.example.rekotlintest.tabs

import android.view.View
import android.view.ViewGroup
import com.example.rekotlintest.R
import com.example.rekotlintest.core.ViewComponent
import com.example.rekotlintest.core.inflate
import com.example.rekotlintest.toprate.FavoriteRouter
import com.example.rekotlintest.toprate.TopRateRouter
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.rekotlin.DispatchFunction

class TabNavigationBar(parent: ViewGroup, dispatch: DispatchFunction) : ViewComponent {

    private val bottomNavigationView: BottomNavigationView = parent.inflate(R.layout.component_bottom_navigation)
    override val view: View
        get() = bottomNavigationView

    init {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.tab_top_rate -> {
                    dispatch(TopRateRouter.openTopScreen())
                }
                R.id.tab_favorite -> {
                    dispatch(FavoriteRouter.openTopScreen())
                }
            }
            true
        }
    }

    fun navigateToTab(id: String) {
        if (id == TabRouter.TOP_RATE) {
            bottomNavigationView.selectedItemId = R.id.tab_top_rate
        } else {
            bottomNavigationView.selectedItemId = R.id.tab_favorite
        }
    }
}
