package com.example.rekotlintest.tabs

import android.view.ViewGroup
import com.example.rekotlintest.core.RouterWithKodein
import com.example.rekotlintest.core.attach
import com.example.rekotlintest.core.setVisible
import com.example.rekotlintest.toprate.FavoriteRouter
import com.example.rekotlintest.toprate.TopRateRouter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.rekotlinrouter.*
import timber.log.Timber

private fun tabRouterPrivateBindings(rootView: ViewGroup) = Kodein.Module(name = "TabRouterModule") {
    val tabContent = "tabContent"

    bind<TabNavigationBar>() with singleton { TabNavigationBar(rootView, instance()) }
    bind<TabHost>() with singleton { TabHost(rootView) }
    bind<ViewGroup>(tabContent) with singleton { instance<TabHost>().contentView }

    bind<TopRateRouter>() with singleton { TopRateRouter(instance(tabContent), instance()) }
    bind<FavoriteRouter>() with singleton { FavoriteRouter(instance(tabContent), instance()) }
}

class TabRouter(
    rootView: ViewGroup,
    patentKodein: Kodein,
    privateBindings: Kodein.Module = tabRouterPrivateBindings(rootView)
) : RouterWithKodein(rootView, patentKodein, privateBindings) {
    companion object {
        const val TOP_RATE: RouteElementIdentifier = "top_rate"
        const val FAVORITE: RouteElementIdentifier = "favorite"
    }

    private val root: ViewGroup by instance(TAG_ROOT_VIEW)
    private val tabs: TabNavigationBar by instance()
    private val tabsHost: TabHost by instance()

    private val topRateRouter: TopRateRouter by instance()
    private val favoriteRouter: FavoriteRouter by instance()

    init {
        root.attach(tabs)
        root.attach(tabsHost)
    }

    override fun pushRouteSegment(
        routeElementIdentifier: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ): Routable {
        Timber.d("pushRouteSegment: $routeElementIdentifier")
        return routeTo(routeElementIdentifier)
    }

    override fun popRouteSegment(
        routeElementIdentifier: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ) {
        Timber.d("popRouteSegment: $routeElementIdentifier")
    }

    override fun changeRouteSegment(
        from: RouteElementIdentifier,
        to: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ): Routable {
        Timber.d("changeRouteSegment: from: $from to: $to")
        return routeTo(to)
    }

    private fun routeTo(identifier: RouteElementIdentifier): Routable {
        tabs.view.setVisible(true)
        return if (identifier == TopRateRouter.TOP_RATE_ROOT_CONTENT) {
            tabs.navigateToTab(TOP_RATE)
            tabsHost.navigateToTab(topRateRouter.viewComponent)
            topRateRouter
        } else {
            tabs.navigateToTab(FAVORITE)
            tabsHost.navigateToTab(favoriteRouter.viewComponent)
            favoriteRouter
        }
    }
}