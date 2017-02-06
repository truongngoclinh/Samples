package samples.linhtruong.com.base.loop;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import samples.linhtruong.com.utils.LogUtils;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/6/17 - 15:38.
 * @organization VED
 */

public class MainUILoop {

    private static volatile MainUILoop mInstance;
    private Handler mHandler;

    private MainUILoop() {}

    public static MainUILoop getInstance() {
        if (mInstance == null) {
            synchronized (MainUILoop.class) {
                if (mInstance == null) {
                    mInstance = new MainUILoop();
                }
            }
        }

        return mInstance;
    }

    public void init() {
        LogUtils.d("init");
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                LogUtils.d("ui handler thread id = " + Thread.currentThread().getId());
                Runnable runnable = (Runnable) msg.obj;
                if (runnable != null) {
                    runnable.run();
                } else {
                    LogUtils.e("ui handler null");
                }
            }
        };
    }

    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public void postDelay(Runnable runnable, long delay) {
        mHandler.postDelayed(runnable, delay);
    }

    public void cancelPost(Runnable runnable) {
        mHandler.removeCallbacks(runnable);
    }
}
