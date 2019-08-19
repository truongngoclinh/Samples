package com.example.rekotlintest.toprate

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rekotlintest.AppConst
import com.example.rekotlintest.AppState
import com.example.rekotlintest.R
import com.example.rekotlintest.core.ActivityState
import com.example.rekotlintest.core.ItemInteractor
import com.example.rekotlintest.core.ViewComponent
import com.example.rekotlintest.core.inflate
import com.example.rekotlintest.data.Movie
import kotlinx.android.synthetic.main.fragment_tab_top_rate.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.rekotlin.BlockSubscriber
import org.rekotlin.DispatchFunction
import org.rekotlin.Store
import org.rekotlin.StoreSubscriber
import org.rekotlinrouter.SetRouteSpecificData
import timber.log.Timber

class TopRateRoot(val parent: ViewGroup, override val kodein: Kodein) : ViewComponent, KodeinAware,
        StoreSubscriber<TopRateState> {
    override val view: ViewGroup = parent.inflate(R.layout.fragment_tab_top_rate)
    private val context: Context by instance()
    private val store: Store<AppState> by instance()
    private val dispatch: DispatchFunction by instance()

    private val listView: RecyclerView by lazy { view.findViewById<RecyclerView>(R.id.lstTopRate) }
    private lateinit var adapter: TopRateAdapter
    private lateinit var listener: ItemInteractor<Movie>

    override fun onAttach() {
        super.onAttach()

        initView()
        initSubscriber()
    }

    private fun initView() {
        adapter = TopRateAdapter(object : ItemInteractor<Movie> {
            override fun onItemClick(data: Movie) {
                Timber.d("onClick: $data")
                dispatch(SetRouteSpecificData(arrayListOf(AppConst.DETAIL_ACTIVITY), data))
            }
        })
        listView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@TopRateRoot.adapter
        }
    }

    private fun initSubscriber() {
        store.subscribe(this) {
            it.select { appState -> appState.topRateState }.skipRepeats { oldState, newState -> oldState == newState }
        }
    }

    override fun newState(state: TopRateState) {
        Timber.d("Receiver new TopRateState: ${state.topRateMovies.size}")
        if (state.topRateMovies.size > 0) {
            adapter.update(state.topRateMovies)
        }
    }

    override fun onDetach() {
        super.onDetach()
        store.unsubscribe(this)
    }
}