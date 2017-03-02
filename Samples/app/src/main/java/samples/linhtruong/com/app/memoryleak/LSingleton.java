package samples.linhtruong.com.app.memoryleak;

import java.lang.ref.WeakReference;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/20/17 - 14:41.
 * @organization VED
 */

public class LSingleton {

    private static volatile LSingleton mInstance;

    public static LSingleton getInstance() {
        if (mInstance == null) {
            synchronized (LSingleton.class) {
               if (mInstance == null) {
                   mInstance = new LSingleton();
               }
            }
        }

        return mInstance;
    }

    private LSingleton() {

    }

    /**
     * This is memory leak
     * */
    private ArrayBlockingQueue<LCallback> mCallbackQueueLeak;

    public void doTaskLeak(LCallback callback) {
        /**
         *  this is memory leak
         * */
        mCallbackQueueLeak.add(callback);

        /* run task */
    }

    /**
     * Solution
     * */
    private ArrayBlockingQueue<WeakReference<LCallback>> mCallBackQueue;

    public void doTask(LCallback callback) {
        mCallBackQueue.add(new WeakReference<>(callback));

        /* run task */
    }
}
