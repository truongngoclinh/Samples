package samples.linhtruong.com.app.multithreading.basic;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 10/26/17 - 15:05.
 * @organization VED
 */

public class ReentrantLock {

    private static ReentrantLockWorker mWorker = new ReentrantLockWorker();

    public static void main(String args[]) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mWorker.firstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mWorker.secondThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mWorker.finish();
    }
}

class ReentrantLockWorker {

    private int mCount = 0;
    private Lock mLock = new java.util.concurrent.locks.ReentrantLock();
    private Condition mCondition = mLock.newCondition();

    public void increment() {
        for (int i = 0; i < 10000; i++) {
            mCount++;
        }
    }

    public void firstThread() throws InterruptedException {
        mLock.lock();
        System.out.println("Waiting...");
        mCondition.await();
        System.out.println("Woken up!");

        try {
            increment();
        } finally {
            mLock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        mLock.lock();
        Thread.sleep(1000);
        System.out.println("Enter key to wake up");
        new Scanner(System.in).nextLine();
        System.out.println("Received key");
        mCondition.signal();

        try {
            increment();
        } finally {
            mLock.unlock();
        }
    }

    public void finish() {
        System.out.println("Count = " + mCount);
    }
}
