package com.example.rekotlintest.favorite

import android.view.ViewGroup
import com.example.rekotlintest.R
import com.example.rekotlintest.core.ViewComponent
import com.example.rekotlintest.core.inflate
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import timber.log.Timber

class FavoriteRoot(parent: ViewGroup, override val kodein: Kodein) : ViewComponent, KodeinAware {
    override val view: ViewGroup = parent.inflate(R.layout.fragment_tab_favorite)

    override fun onAttach() {
        super.onAttach()

        Timber.d("onAttach")
    }
}