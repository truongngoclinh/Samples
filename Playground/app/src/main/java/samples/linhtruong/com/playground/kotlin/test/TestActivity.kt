package samples.linhtruong.com.playground.kotlin.test

import android.app.Activity
import android.os.Handler
import android.util.Log


/**
 * @author linhtruong
 */
class TestActivity : Activity() {
    private val TAG = this.javaClass.name


    fun test() {
/*        var thread = Thread(Runnable {
            Log.d(TAG,"hi run")
        })

        var handler = Handler()
        handler.post {
            Log.d(TAG, "action")
        }
        handler.removeCallbacks {
            Log.d(TAG, "action")
        }*/

        val runnable = Runnable { Log.d(TAG, "action") }
        val handler = Handler()
        handler.post(runnable)
        handler.removeCallbacks(runnable)
    }
}