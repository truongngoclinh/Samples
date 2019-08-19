package com.example.rekotlintest.toprate

import android.view.ViewGroup
import com.example.rekotlintest.core.RouterWithKodein
import com.example.rekotlintest.core.ViewComponentRouter
import com.example.rekotlintest.favorite.FavoriteRoot
import com.example.rekotlintest.toprate.TopRateRouter.Companion.NAME_MODULE_TOP_RATE
import com.example.rekotlintest.toprate.TopRateRouter.Companion.TOP_RATE_ROOT
import com.example.rekotlintest.toprate.TopRateRouter.Companion.TOP_RATE_ROOT_CONTENT
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.rekotlin.Action
import org.rekotlinrouter.*
import timber.log.Timber

fun favoritePrivateBindings() = Kodein.Module(NAME_MODULE_TOP_RATE) {
    bind<FavoriteRoot>() with singleton { FavoriteRoot(instance(RouterWithKodein.TAG_ROOT_VIEW), kodein) }
    bind<ViewGroup>(FavoriteRouter.FAVORITE_ROOT) with singleton { instance<FavoriteRoot>().view }
}

class FavoriteRouter(
    rootView: ViewGroup,
    parentKodein: Kodein,
    privateBindings: Kodein.Module = favoritePrivateBindings()
) : RouterWithKodein(rootView, parentKodein, privateBindings), ViewComponentRouter, KodeinAware {
    companion object {
        internal const val NAME_MODULE_FAVORITE: String = "FavoriteRouter"
        internal const val FAVORITE_ROOT: String = "favorite_root"
        internal const val FAVORITE_ROOT_CONTENT: RouteElementIdentifier = "favorite_root_content"

        fun openTopScreen(): Action = SetRouteAction(arrayListOf(FAVORITE_ROOT_CONTENT))
    }

    private val favoriteRoot: FavoriteRoot by instance()

    override val viewComponent = favoriteRoot

    override fun changeRouteSegment(
        from: RouteElementIdentifier,
        to: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ): Routable {
        Timber.d("changeRouteSegment")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun popRouteSegment(
        routeElementIdentifier: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ) {
        Timber.d("popRouteSegment")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pushRouteSegment(
        routeElementIdentifier: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ): Routable {
        Timber.d("pushRouteSegment")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}