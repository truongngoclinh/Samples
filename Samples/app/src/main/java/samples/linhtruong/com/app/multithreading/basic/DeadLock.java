package samples.linhtruong.com.app.multithreading.basic;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * When multi hreads are keeping lock on each other objects
 *
 * @author linhtruong
 * @date 10/26/17 - 15:28.
 * @organization VED
 */

public class DeadLock {

    private static DeadLockWorker mWorker = new DeadLockWorker();

    public static void main(String[] args) {
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

class DeadLockWorker {

    private Lock mLock1 = new ReentrantLock();
    private Lock mLock2 = new ReentrantLock();

    private Account mAccount1 = new Account();
    private Account mAccount2 = new Account();

    // solution, checking lock status
    private void acquireLock(Lock lock1, Lock lock2) {
        while (true) {
            boolean isAcquiredLock1 = false;
            boolean isAcquiredLock2 = false;

            try {
                isAcquiredLock1 = lock1.tryLock();
                isAcquiredLock2 = lock2.tryLock();
            } finally {
                if (isAcquiredLock1 & isAcquiredLock2) {
                    return;
                }

                if (isAcquiredLock1) {
                    lock1.unlock();
                }

                if (isAcquiredLock2) {
                    lock2.unlock();
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void firstThread() throws InterruptedException {
        /*mLock1.lock();

        Thread.sleep(500); // just ensure secondThread acquire mLock2 first, then deadlock occured
        mLock2.lock();*/
        acquireLock(mLock1, mLock2);

        try {
            Account.transfer(mAccount1, mAccount2, new Random().nextInt(100));
        } finally {
            mLock1.unlock();
            mLock2.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        /*// deadlock occured if the order is not consistent
        mLock2.lock();
        mLock1.lock();*/

//        mLock1.lock();
//        mLock2.lock();
        acquireLock(mLock2, mLock1);

        try {
            Account.transfer(mAccount2, mAccount1, new Random().nextInt(100));
        } finally {
            mLock1.unlock();
            mLock2.unlock();
        }
    }

    public void finish() {
        System.out.println("Account 1 balance = " + mAccount1.getBalance());
        System.out.println("Account 1 balance = " + mAccount2.getBalance());
        System.out.println("Total amount = " + (mAccount2.getBalance() + mAccount1.getBalance()));
    }
}

class Account {

    private int mBlance = 10000;

    public int getBalance() {
        return mBlance;
    }

    public void withdraw(int amount) {
        mBlance -= amount;
    }

    public void deposit(int amount) {
        mBlance += amount;
    }

    public static void transfer(Account acc1, Account acc2, int amount) {
        acc1.withdraw(amount);
        acc2.deposit(amount);
    }
}
