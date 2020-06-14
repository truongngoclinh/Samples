package com.example.rekotlintest.toprate

import android.content.Context
import android.content.Intent
import android.view.ViewGroup
import com.example.rekotlintest.appStore
import com.example.rekotlintest.core.RouterWithKodein
import com.example.rekotlintest.core.ViewComponentRouter
import com.example.rekotlintest.data.Movie
import com.example.rekotlintest.detail.DetailActivity
import com.example.rekotlintest.detail.DetailRouter
import com.example.rekotlintest.toprate.TopRateRouter.Companion.NAME_MODULE_TOP_RATE
import com.example.rekotlintest.toprate.TopRateRouter.Companion.TOP_RATE_ROOT
import com.example.rekotlintest.toprate.TopRateRouter.Companion.TOP_RATE_ROOT_CONTENT
import com.squareup.moshi.Moshi
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import org.rekotlin.Action
import org.rekotlinrouter.*
import timber.log.Timber

fun topRatePrivateBindings() = Kodein.Module(NAME_MODULE_TOP_RATE) {
    bind<TopRateRoot>() with singleton { TopRateRoot(instance(RouterWithKodein.TAG_ROOT_VIEW), kodein) }
    bind<ViewGroup>(TOP_RATE_ROOT) with singleton { instance<TopRateRoot>().view }
}

class TopRateRouter(
        rootView: ViewGroup,
        parentKodein: Kodein,
        privateBindings: Kodein.Module = topRatePrivateBindings()
) : RouterWithKodein(rootView, parentKodein, privateBindings), ViewComponentRouter, KodeinAware {
    companion object {
        internal const val NAME_MODULE_TOP_RATE: String = "TopRateRouter"
        internal const val TOP_RATE_ROOT: String = "top_rate_root"
        internal const val TOP_RATE_ROOT_CONTENT: RouteElementIdentifier = "top_rate_root_content"

        fun openTopScreen(): Action = SetRouteAction(arrayListOf(TOP_RATE_ROOT_CONTENT))
    }

    private val topRateRoot: TopRateRoot by instance()
    private val context: Context by instance("ActivityContext")
    private val moshi: Moshi by instance()

    override val viewComponent = topRateRoot

    override fun changeRouteSegment(
            from: RouteElementIdentifier,
            to: RouteElementIdentifier,
            animated: Boolean,
            completionHandler: RoutingCompletionHandler
    ): Routable {
        Timber.i("changeRouteSegment")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun popRouteSegment(
            routeElementIdentifier: RouteElementIdentifier,
            animated: Boolean,
            completionHandler: RoutingCompletionHandler
    ) {
        Timber.i("popRouteSegment")
    }

    override fun pushRouteSegment(
            routeElementIdentifier: RouteElementIdentifier,
            animated: Boolean,
            completionHandler: RoutingCompletionHandler
    ): Routable {
        Timber.i("pushRouteSegment")
        val intent = Intent(context, DetailActivity::class.java)
        val routes = appStore.state.navigationState.route
        val data = appStore.state.navigationState.getRouteSpecificState<Movie>(routes)
        val jsonAdapter = moshi.adapter(Movie::class.java)
        intent.putExtra(DetailActivity.KEY_MOVIE, jsonAdapter.toJson(data))
        context.startActivity(intent)
        return DetailRouter(context)
    }
}