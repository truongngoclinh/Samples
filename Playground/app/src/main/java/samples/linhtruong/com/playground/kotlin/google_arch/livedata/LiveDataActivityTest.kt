package samples.linhtruong.com.playground.kotlin.google_arch.livedata

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.ComponentName
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log


/**
 * @author linhtruong
 */
class LiveDataActivityTest : AppCompatActivity() {
    private lateinit var vm: LiveDataViewModelTest
    private var livedataService: LiveDataServiceTest? = null
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as LiveDataServiceTest.ServiceBinder
            livedataService = binder.getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = ViewModelProviders.of(this).get(LiveDataViewModelTest::class.java)
        vm.serviceData.observe(this, Observer {})
    }
}
