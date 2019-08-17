package com.example.rekotlintest

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.rekotlin.Action
import org.rekotlin.BlockSubscriber
import org.rekotlin.Store
import org.rekotlin.StoreSubscriber

val mainStore = Store(
        reducer = AppReducer::counterReducer,
        state = null
)

class MainActivity : AppCompatActivity(), StoreSubscriber<Appstate> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        increase.setOnClickListener {
            mainStore.dispatch(CounterAction.Increase())
        }

        decrease.setOnClickListener {
            mainStore.dispatch(CounterAction.Decrease())
        }

//        mainStore.subscribe(this)
        val appStateSubscriber = BlockSubscriber<Appstate> {
            counter.text = it.counter.toString()
        }
        mainStore.subscribe(appStateSubscriber)
    }

    override fun newState(state: Appstate) {
        Log.d(TAG, "state is: " + state.counter)
        counter.text = state.counter.toString()
    }

    private val TAG = "LINH"
}
