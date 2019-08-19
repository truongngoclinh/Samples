package com.example.rekotlintest

import android.content.Context
import android.os.Bundle
import android.system.Os.bind
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.rekotlintest.core.base.BaseActivity
import com.example.rekotlintest.tabs.TabRouter
import com.example.rekotlintest.toprate.TopRateAction
import com.example.rekotlintest.toprate.TopRateRouter
import com.example.rekotlintest.toprate.TopRateState
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.android.subKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.rekotlin.DispatchFunction
import org.rekotlin.StoreSubscriber
import org.rekotlinrouter.Route
import org.rekotlinrouter.Router
import org.rekotlinrouter.SetRouteAction
import timber.log.Timber

class MainActivity : BaseActivity(), KodeinAware, StoreSubscriber<TopRateState> {
    override val kodein by subKodein(kodein()) {
        bind<Context>() with provider {
            this@MainActivity
        }

        bind<TopRateAction.LoadTopRateMovies>() with provider {
            TopRateAction.LoadTopRateMovies(instance(), instance(), instance())
        }
    }

    val dispatch: DispatchFunction by instance()
    private val loadTopRateMovies: TopRateAction.LoadTopRateMovies by instance()
    private lateinit var router: Router<AppState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        router = Router(
                store = appStore,
                rootRoutable = TabRouter(container, kodein),
                stateTransform = { it.select { it.navigationState } }
        )

        appStore.subscribe(this) {
            it.select { appState ->
                appState.topRateState
            }.skipRepeats { oldState, newState ->
                oldState == newState
            }
        }

        if (appStore.state.navigationState.route.isEmpty()) {
            appStore.dispatch(SetRouteAction(arrayListOf(TopRateRouter.TOP_RATE_ROOT_CONTENT)))
        }


        Timber.d("Started dispatch load top rate movies")
        dispatch(loadTopRateMovies())
    }

    override fun newState(state: TopRateState) {
        renderLoading(state.isLoading)
    }

    override fun onDestroy() {
        super.onDestroy()
        appStore.unsubscribe(this)
        appStore.unsubscribe(router)
    }
}
