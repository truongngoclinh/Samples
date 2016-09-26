package samples.linhtruong.com.app.uireactive.task;

import android.os.HandlerThread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Truong on 9/26/16 - 20:18.
 * Description: manage collection of thread: io, db read, ui, compute
 */

public class TaskScheduler {

    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static final long KEEP_ALIVE_TIME = 10;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    // common IO i.e. network, db write
    static ThreadPoolExecutor mThreadPool = new ThreadPoolExecutor(
            NUMBER_OF_CORES,
            NUMBER_OF_CORES,
            KEEP_ALIVE_TIME,
            KEEP_ALIVE_TIME_UNIT,
            new LinkedBlockingDeque<Runnable>(),
            new IOThreadFactory(),
            new ThreadPoolExecutor.DiscardPolicy()

    );

    static {
        mThreadPool.allowsCoreThreadTimeOut();
    }

    // db read
    static HandlerThread mDbReadThread = new HandlerThread("db-r");

    static {
        mDbReadThread.start();
    }

    public static Scheduler IO = Schedulers.from(mThreadPool);
    public static Scheduler COMPUTE = Schedulers.computation();
    public static Scheduler UI = AndroidSchedulers.mainThread();
    public static Scheduler DBRead = AndroidSchedulers.from(mDbReadThread.getLooper());

    private static class IOThreadFactory implements ThreadFactory {

        private AtomicInteger index = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "io-" + index.getAndIncrement());
        }
    }
}
