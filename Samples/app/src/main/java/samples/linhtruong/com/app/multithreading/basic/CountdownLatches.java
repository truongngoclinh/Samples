package samples.linhtruong.com.app.multithreading.basic;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * synchronization aid, usage in case of waiting for several threads to finish
 * and we dont have threads intance to use join()
 *
 * @author linhtruong
 * @date 10/26/17 - 11:51.
 * @organization VED
 */

public class CountdownLatches {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        ArrayList<Processor> processors = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            Processor processor = new Processor(latch, i + 1);
            executorService.submit(processor);
            processors.add(processor);
        }

        executorService.shutdown();
//        try {
//            executorService.awaitTermination(1, TimeUnit.HOURS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println("Waiting for all threads completed");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed.");
        int result = 0;
        for (Processor processor : processors) {
            result += processor.getWait();
        }
        System.out.print("Combined result = " + result);
    }
}

class Processor implements Runnable {

    private CountDownLatch mLatch;
    private int mWait;

    public Processor(CountDownLatch latch, int wait) {
        mLatch = latch;
        mWait = wait;
    }

    @Override
    public void run() {
        System.out.println("Started.");
        try {
            Thread.sleep(mWait * 2000);
            mWait *= 2000;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mLatch.countDown();
    }

    public int getWait() {
        return mWait;
    }
}
