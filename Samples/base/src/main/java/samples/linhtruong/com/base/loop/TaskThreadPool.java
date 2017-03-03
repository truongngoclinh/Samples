package samples.linhtruong.com.base.loop;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/6/17 - 15:59.
 * @organization VED
 */

public class TaskThreadPool {

    private static final int KEEP_ALIVE_TIME = 10;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors();

    private ThreadPoolExecutor mThreadPoolExecutor;

    private static volatile TaskThreadPool mPool;

    private TaskThreadPool() {
        mThreadPoolExecutor = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, new LinkedBlockingDeque<Runnable>());
    }

    public static TaskThreadPool getPool() {
        if (mPool == null) {
            synchronized (TaskThreadPool.class) {
                if (mPool == null) {
                    mPool = new TaskThreadPool();
                }
            }
        }

        return mPool;
    }

    public void addTask(Runnable runnable) {
       mThreadPoolExecutor.execute(runnable);
    }
}
