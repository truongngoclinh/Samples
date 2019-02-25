package samples.linhtruong.com.playground.kotlin.google_arch.livedata

import android.app.IntentService
import android.app.Service
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Binder
import android.os.IBinder


/**
 * @author linhtruong
 */
class LiveDataServiceTest : Service() {
    private val binder = ServiceBinder()

    override fun onBind(intent: Intent?): IBinder = binder

    inner class ServiceBinder : Binder() {
        fun getService(): LiveDataServiceTest = this@LiveDataServiceTest
    }
}