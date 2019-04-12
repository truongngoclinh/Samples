package samples.linhtruong.com.playground.kotlin.google_arch.livedata

import android.app.IntentService
import android.app.Service
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import timber.log.Timber


/**
 * @author linhtruong
 */
class LiveDataServiceTest : Service() {
    private val binder = ServiceBinder()
    private val data = MutableLiveData<Int>()

    inner class ServiceBinder : Binder() {
        fun getService(): LiveDataServiceTest = this@LiveDataServiceTest
        fun getData(): LiveData<Int> = this@LiveDataServiceTest.data
    }

    override fun onBind(intent: Intent?): IBinder = binder
    fun updateProgress() {
        for (i in 1..10) {
            Timber.d("post progress: $i")
//            data.postValue(i)
            data.value = i
            Thread.sleep(200)
        }
    }
}
