package samples.linhtruong.com.app.multithreading.threadcommunication;

import android.os.Handler;
import samples.linhtruong.com.base.BaseApplication;

/**
 * Describe how runOnUiThread() works
 *
 * @author linhtruong
 * @date 8/22/17 - 16:07.
 * @organization VED
 */

public class CustomApplication extends BaseApplication {

    private long mUiThread;
    private Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        mUiThread = Thread.currentThread().getId();
        mHandler = new Handler();
    }

    public void customRunOnUiThread(Runnable action) {
        if (Thread.currentThread().getId() == mUiThread) {
            // call from activity instance
            action.run();
        } else {
            mHandler.post(action);
        }
    }
}
