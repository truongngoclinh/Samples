package samples.linhtruong.com.app.multithreading.threadcommunication;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Test thread communication using {@link java.util.concurrent.BlockingQueue}
 *
 * @author linhtruong
 * @date 8/16/17 - 15:00.
 * @organization VED
 */

public class BlockingQueueTest {

    private static final int QUEUE_SIZE = 10;
    private static BlockingQueue<Integer> mBlockingQueue = new LinkedBlockingQueue<>(QUEUE_SIZE);

    public static void main(String[] args) {
        Thread writer = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    try {
                        mBlockingQueue.put(i++); // block the thread if queue is full
                        System.out.println("writing data: " + i);
                        if (i == 10) {
                            System.out.println("stop writing at value: " + i);
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread reader = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("getting data: " + mBlockingQueue.take()); // block the thread if queue is empty
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        System.out.println("start transferring...");
        writer.start();
        reader.start();
    }

}
