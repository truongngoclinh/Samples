package samples.linhtruong.com.app.multithreading;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This is class is an example for intrinsic lock test.
 * It uses consumer-producer pattern which means:
 * - We have a consumer thread that consumes data while a produer thread that produces data.
 * - Both threads are working on a shared resource which is a linked list.
 * - Producer thread adds item to the list when the list is not full
 * - Consumer thread removes item from the list when the list is empty
 *
 * We try some cases:
 * - block-level lock & release monitor with a lock object (synchronized(object), object.wait(), object.notify)
 * - block-level just lock the enclosing object instance (synchronized(this)), enclosing class instance (synchronized(this.getClass())
 * - method-level just lock the enclosing object instance (synchronized(method)), enclosing class instance (synchronized(static method))
 * - replace sychronized keyword with reentrantLock
 *
 *
 * @author linhtruong
 * @date 8/10/17 - 15:40.
 * @organization VED
 */

public class IntrinsicLockTest {

    private static ConsumerProducer mCP;

    private static Thread mProducer = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                mCP.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    private static Thread mConsumer = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                mCP.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });


    public static void main(String[] args) {
        mCP = new ConsumerProducer();
        System.out.println("Producer thread started");
        mProducer.start();
        System.out.println("Consumer thread started");
        mConsumer.start();
    }

    private static class ConsumerProducer {

        public ConsumerProducer() {
            System.out.println("Created CP");
            mShareResource = new LinkedList<>();
            mLock = new Object();
            mReentrantLock = new ReentrantLock();
        }

        private final int SIZE = 10;
        private LinkedList<Integer> mShareResource;

        // intrinsc block-level object
        private Object mLock;

        // replace synchronized keyword
        private ReentrantLock mReentrantLock;
        private ReentrantReadWriteLock mReentrantReadWriteLock; // usually for special case such as: much different between number of writtings & readings

        private void produce() throws InterruptedException {
            int value = 0;
            while (true) {
                synchronized (mLock) { // object lock
//                synchronized (this) { // block-level enclosing object instance
//                mReentrantLock.lock();
//                try {
                    while (mShareResource.size() == SIZE) {
                        /**
                         *  Thread suspends itself and waiting for signal from other threads with mLock.notify()
                         *  then take ownership of the monitor
                         * */
                        System.out.println("lockWait, producer suspended");
//                        break;
                        mLock.wait(); // object lock
                    }

                    mShareResource.add(value++);
                    System.out.println("produces " + value);
//                    if (value == SIZE * 2) break; // for no lock test case
                    if (value == SIZE * 2) break;
                    mLock.notify(); // object lock
//                }
                }
//                finally {
//                    mReentrantLock.unlock();
//                }

//                if (value == SIZE) break;
            }
        }

        private void consume() throws InterruptedException {
            int value = 0;
            while (true) {
                synchronized (mLock) {
//                synchronized (this) {
//                mReentrantLock.lock();
//                try {
                    while (mShareResource.size() == 0) {
                        System.out.println("lockWait, consumer suspended");
//                        break;
                        mLock.wait();
                    }

                    value = mShareResource.removeFirst();

//                    value = mShareResource.removeLast();
                    System.out.println("consumes " + value);
                    if (value == SIZE * 2) break;
//                    if (value == 10) break;
                    mLock.notify();
//                }
                }
//                finally {
//                    mReentrantLock.unlock();
//                }

//                if (mShareResource.size() == 0) break;
            }
        }
    }

}
