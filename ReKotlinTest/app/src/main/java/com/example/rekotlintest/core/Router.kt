package com.example.rekotlintest.core

import android.view.ViewGroup
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import org.rekotlinrouter.Routable

/**
 * Base Router class with [Kodein] instances that hold different responsibility respectively.
 * The responsibility of this class includes:
 *
 * 1. Separates [Kodein.Module] bindings into public and private.
 * 2. Provides an easy way to swap private [Kodein.Module] bindings in unit tests.
 *
 * @param rootView root view of this router.
 * @param parentKodein the [Kodein] instance passed by parent router.
 * @param privateBindings the [Kodein.Module] that only used in this class.
 * @param exposedBindings the [Kodein.Module] that could be shared with children routers, e.g. services etc.
 */
abstract class RouterWithKodein(
    rootView: ViewGroup,
    parentKodein: Kodein,
    privateBindings: Kodein.Module,
    exposedBindings: Kodein.Module? = null
) : KodeinAware, Routable {

    companion object {
        const val TAG_ROOT_VIEW = "routerRootView"
    }

    // only for this router
    override val kodein: Kodein = Kodein {
        extend(parentKodein)
        import(privateBindings)

        bind<ViewGroup>(TAG_ROOT_VIEW) with singleton { rootView }
        bind<Kodein>() with singleton { kodeinForChildren }
    }
    // only for children!
    private val kodeinForChildren: Kodein =
        if (exposedBindings != null) {
            Kodein {
                extend(parentKodein)
                import(exposedBindings)
            }
        } else {
            parentKodein
        }
}

interface ViewComponentRouter : Routable {
    val viewComponent: ViewComponent
}
