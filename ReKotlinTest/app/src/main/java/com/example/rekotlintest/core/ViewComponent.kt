package com.example.rekotlintest.core

import android.view.View
import android.view.ViewGroup
import org.kodein.di.KodeinAware

/**
 * The minimal contract describing a ViewComponent.
 *
 * View components encapsulate behavior and expose their UI to their parent.
 * View components are part of a tree-shaped hierarchy, the parent view component only needs the child's
 * [ViewComponent.view] to embed its UI in the parent UI. All other interaction is facilitated by the Redux
 * [org.rekotlin.Store].
 */
interface ViewComponent {

    /**
     * View to embed
     */
    val view: View

    /**
     * Parent ViewComponents should call [onAttach] after attaching a child's [view] to the view hierarchy.
     *
     * [ViewGroup.attach] will automatically call this after attaching a [view].
     *
     * This is a good time to subscribe to the Redux store and/or dispatch initialization actions to warm up caches etc.
     */
    fun onAttach() {}

    /**
     * Parent ViewComponents should call [onDetach] after detaching a child's [view] to the view hierarchy.
     *
     * [ViewGroup.detach] will automatically call this after detaching a [view].
     *
     * This is a good time to unsubscribe to the Redux store and cleanup internal state. This should not destroy the
     * ViewComponent, [onAttach] may be called again to reuse this ViewComponent
     */
    fun onDetach() {}
}

fun ViewGroup.attach(viewComponent: ViewComponent) {
    this.addView(viewComponent.view)
    viewComponent.onAttach()
}

fun ViewGroup.detach(viewComponent: ViewComponent) {
    for (i in 0..this.childCount) {
        if (getChildAt(i) == viewComponent.view) {
            this.removeViewAt(i)
            viewComponent.onDetach()
            break
        }
    }
}

fun ViewGroup.attachIfNotAttached(viewComponent: ViewComponent) {
    for (i in 0..this.childCount){
        if (getChildAt(i) == viewComponent.view){
            return
        }
    }

    attach(viewComponent)
}

/**
 * A view component which could potentially describe the minimal contract for a tab view.
 */
interface TabViewComponent : HasTitle, KodeinAware, ViewComponent

interface HasTitle {
    val title: String
}
