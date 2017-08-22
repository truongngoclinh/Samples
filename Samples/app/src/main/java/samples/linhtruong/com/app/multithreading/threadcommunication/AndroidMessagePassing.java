package samples.linhtruong.com.app.multithreading.threadcommunication;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.LogPrinter;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseActivity;

import java.util.Random;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Simple sample about android message passing mechanisms using
 * {@link android.os.MessageQueue}
 * {@link android.os.Looper}
 * {@link android.os.Handler}
 * {@link android.os.Message}
 * {@link android.os.MessageQueue.IdleHandler}
 * {@link Thread}
 * <p>
 * User clicks send button will signal worker thread to do long task.
 *
 * Notes:
 * - {@link android.os.Handler} The Handler.Callback inteface doesnt override handleMessage() method of a handler
 *   + return true to handle this message
 *   + return false to pass the message to the handlers own method, handleMessage()
 *
 * @author linhtruong
 * @date 8/16/17 - 15:22.
 * @organization VED
 */

@EActivity(R.layout.activity_android_message_passing)
public class AndroidMessagePassing extends BaseActivity {

    private static final int MSG_SIGNAL = 0;
    public static final String MSG_QUEUE_LOG = "message_queue_log";

    private LooperThread mLooperThread;

    private int mMsgSentCount;
    private ConsumeAndQuitLooperThread mConsumeAndQuitLooperThread;

    private WokerThreadFactory mThreadFactory; // just for thread name specific

    /**
     * test message insert/dispatch
     */
    @Click(R.id.btnSend)
    void onClick() {
        if (mLooperThread != null) {
            Handler handler = mLooperThread.getHandler();
            if (handler != null) {
                Message msg = handler.obtainMessage(MSG_SIGNAL);
                handler.sendMessage(msg);
            }
        }
    }

    /**
     * Test terminate worker thread in its idle time
     * after process msg with randomness of the insertion time.
     * <p>
     * The condition for terminating are:
     * - in thread idling time
     * - number of msg processed >= 10
     * <p>
     * so we ensure that at least 10 msgs were processed before interupting this consumer thread.
     */
    @Click(R.id.btnSendOnce)
    void onClickSendOnce() {
        System.out.println("onClick btnSendOnce");
        mMsgSentCount = 0;
        // The msg insertion done from multiple threads concurrently
        for (int i = 0; i < 5; i++) {
            if (mConsumeAndQuitLooperThread.isAlive()) {
                mThreadFactory.newThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            int random = new Random().nextInt(5);
                            SystemClock.sleep(random);
                            System.out.println("Consumer thread state: " + mConsumeAndQuitLooperThread.getState() + " \n-- " + Thread.currentThread().getName()
                                    + " sleeps for " + random + " then send new msg #" + mMsgSentCount);
                            mConsumeAndQuitLooperThread.enqueueNewEmptyMsg(mMsgSentCount);
                            mMsgSentCount++;
                        }
                    }
                }).start();
            }
        }
    }

    @AfterViews
    void afterViews() {
        mLooperThread = new LooperThread();
        mLooperThread.start();
        System.out.println("Start worker thread, waiting for signal...");

        mConsumeAndQuitLooperThread = new ConsumeAndQuitLooperThread();
        mConsumeAndQuitLooperThread.start();
        System.out.println("Start consume&quit worker thread, waiting for signal...");

        mThreadFactory = new WokerThreadFactory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLooperThread.quit();
    }

    private static class LooperThread extends Thread implements Runnable {

        private Handler mHandler; // the handler handles UI thread send msg to msg queue & worker thread receive msg dispatched from its looper.

        public LooperThread() {
        }

        @Override
        public void run() {
            Looper.prepare(); // associate looper & msg queue with this thread
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == MSG_SIGNAL) {
                        longRunningTask();
                    }
                }
            };
            Looper.loop(); // start dispatching msg from msg queue to this thread
        }

        private void longRunningTask() {
            // heavy task
            System.out.println("start longRunningTask...");
        }

        public Handler getHandler() {
            return mHandler;
        }

        public void quit() {
            mHandler.getLooper().quit();
        }
    }

    private static class ConsumeAndQuitLooperThread extends Thread implements MessageQueue.IdleHandler {

        private final int MSG_PROCESSING_LIMIT = 10;

        private Handler mHandler;
        private boolean mIsFirstIdle;
        private int mMsgProcessedCount; // after reached the limit msg processed, we terminated in thread idling time.

        public ConsumeAndQuitLooperThread() {
            mIsFirstIdle = true;
            mMsgProcessedCount = 0;
        }

        @Override
        public void run() {
            Looper.prepare();
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    mMsgProcessedCount++;
                    System.out.println("received a msg #" + msg.what + " processedCount = " + mMsgProcessedCount);
                }
            };

            // for tracing messasge queue
            mHandler.dump(new LogPrinter(Log.DEBUG, MSG_QUEUE_LOG), "'");
            Looper.myLooper().setMessageLogging(new LogPrinter(Log.DEBUG, MSG_QUEUE_LOG));
            //

            Looper.myQueue().addIdleHandler(this);
            Looper.loop();
        }

        @Override
        public boolean queueIdle() {
            System.out.println("================= Oh queue idle! =================");
            if (mIsFirstIdle) { // only pass first invocation, still register IdleHandler
                System.out.println("first idling");
                mIsFirstIdle = false;
                return true;
            }

            if (mMsgProcessedCount >= MSG_PROCESSING_LIMIT) {
                System.out.println("TERMINATE looper, stopped to listen dispatched msg. Thread continues running remaining path of code.");
                mHandler.getLooper().quit();
                return false;
            }

           /* // same as
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mHandler.getLooper().getQueue().removeIdleHandler(this);
            }*/

            return true;
        }

        public void enqueueNewEmptyMsg(int i) {
            mHandler.sendEmptyMessage(i);
        }
    }

    private static class WokerThreadFactory implements ThreadFactory {

        private AtomicInteger threadIndex;
        private String threadName;

        public WokerThreadFactory() {
            threadIndex = new AtomicInteger(1);
        }

        @Override
        public Thread newThread(@NonNull Runnable r) {
            threadName = "Thread#" + threadIndex.getAndIncrement();
            System.out.println(threadName + " is created");
            return new Thread(r, threadName);
        }
    }
}
