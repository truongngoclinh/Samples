package com.example.rekotlintest.core

import android.content.Context
import android.content.Intent
import com.example.rekotlintest.detail.DetailActivity
import org.rekotlinrouter.Routable
import org.rekotlinrouter.RouteElementIdentifier
import org.rekotlinrouter.RoutingCompletionHandler
import timber.log.Timber

val detailRoute: RouteElementIdentifier = "DetailActivity"
val mainRoute: RouteElementIdentifier = "MainActivity"

class RootRoutable(val context: Context) : Routable {
    override fun popRouteSegment(
        routeElementIdentifier: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ) {
        Timber.d("popRouteSegment")
    }

    override fun pushRouteSegment(
        routeElementIdentifier: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ): Routable {
        Timber.d("pushRouteSegment $routeElementIdentifier")
        TODO("not implemented")
    }

    override fun changeRouteSegment(
        from: RouteElementIdentifier,
        to: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ): Routable {
        Timber.d("changeRouteSegment")
        TODO("not implemented")
    }
}

class DetailRoutable(context: Context) : Routable {
    override fun changeRouteSegment(
        from: RouteElementIdentifier,
        to: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ): Routable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun popRouteSegment(
        routeElementIdentifier: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pushRouteSegment(
        routeElementIdentifier: RouteElementIdentifier,
        animated: Boolean,
        completionHandler: RoutingCompletionHandler
    ): Routable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}


object RoutableHelper {
    fun createDetailRoutable(context: Context): DetailRoutable {
        val welcomeIntent = Intent(context, DetailActivity::class.java)
        welcomeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(welcomeIntent)
        return DetailRoutable(context)
    }
}
