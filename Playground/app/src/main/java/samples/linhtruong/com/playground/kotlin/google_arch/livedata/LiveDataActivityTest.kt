package samples.linhtruong.com.playground.kotlin.google_arch.livedata

import android.animation.ObjectAnimator
import android.annotation.TargetApi
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.view.View
import samples.linhtruong.com.playground.R
import kotlinx.android.synthetic.main.activity_livedata_service_test.*
import timber.log.Timber


/**
 * @author linhtruong
 */
class LiveDataActivityTest : AppCompatActivity(), View.OnClickListener {
    private lateinit var livedataService: LiveDataServiceTest
    private lateinit var data: LiveData<Int>
    private var isBound: Boolean = false

    private val connection = object : ServiceConnection {
        @TargetApi(Build.VERSION_CODES.N)
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as LiveDataServiceTest.ServiceBinder
            livedataService = binder.getService()
            data = binder.getData()
            isBound = true
            Timber.d("Service connected.")

            data.observe(this@LiveDataActivityTest, Observer {
                it?.let { value ->
                    Timber.d("received progress: $value")
                    progressBar.setProgress(value * 10, true)
                }
            })
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            Timber.d("Service disconnected.")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedata_service_test)

        val intent = Intent(this, LiveDataServiceTest::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)

        btnStart.setOnClickListener(this)
    }

    override fun onClick(p: View?) {
        if (isBound) {
            livedataService.updateProgress()
        }
    }
}

