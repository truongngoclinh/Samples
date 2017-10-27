package samples.linhtruong.com.app.multithreading.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 10/25/17 - 14:51.
 * @organization VED
 */

public class ThreadPool {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Worker(i));
        }

        executorService.shutdown();
        System.out.println("All tasks submitted.");

        executorService.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("All tasks completed.");

        System.out.println("Time running: " + (System.currentTimeMillis() - start));
    }
}

class Worker implements Runnable {

    private int mId;

    public Worker(int id) {
        mId = id;
    }

    @Override
    public void run() {
        System.out.println("Start worker: " + mId);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed work.");
    }
}
